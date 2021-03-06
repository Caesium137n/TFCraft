package TFC.Blocks;

import java.util.ArrayList;
import java.util.Random;
import TFC.*;
import TFC.Core.ColorizerFoliageTFC;
import TFC.Core.ColorizerGrassTFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.crash.*;
import net.minecraft.creativetab.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.packet.*;
import net.minecraft.pathfinding.*;
import net.minecraft.potion.*;
import net.minecraft.server.*;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.village.*;
import net.minecraft.world.*;
import net.minecraft.world.biome.*;
import net.minecraft.world.chunk.*;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.common.IShearable;

public class BlockCustomTallGrass extends BlockTallGrass implements IShearable
{
    public BlockCustomTallGrass(int par1, int par2)
    {
        super(par1, par2);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return par2 == 1 ? this.blockIndexInTexture : (par2 == 2 ? this.blockIndexInTexture + 16 + 1 : (par2 == 0 ? this.blockIndexInTexture + 16 : this.blockIndexInTexture));
    }

    public int getBlockColor()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrassTFC.getGrassColor(var1, var3);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1)
    {
        return par1 == 0 ? 16777215 : ColorizerFoliageTFC.getFoliageColorBasic();
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return TerraFirmaCraft.proxy.grassColorMultiplier(par1IBlockAccess, par2, par3, par4);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        //        if (world.rand.nextInt(8) != 0)
        //        {
        //            return ret;
        //        }

        ItemStack item = GetSeeds(world.rand);
        if (item != null)
        {
            ret.add(item);
        }
        return ret;
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z) 
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune) 
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }

    @Override
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == TFCBlocks.Grass.blockID || par1 == TFCBlocks.Grass2.blockID || 
                par1 == TFCBlocks.Dirt.blockID || par1 == TFCBlocks.Dirt2.blockID ||
                par1 == TFCBlocks.ClayGrass.blockID || par1 == TFCBlocks.ClayGrass2.blockID ||
                par1 == TFCBlocks.PeatGrass.blockID ||
                par1 == Block.tilledField.blockID;
    }
    
    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return (par1World.getFullBlockLightValue(par2, par3, par4) >= 8 || 
        		par1World.canBlockSeeTheSky(par2, par3, par4)) && 
        		this.canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
    }

    public static ItemStack GetSeeds(Random R)
    {
        ItemStack is = null;
        if(R.nextInt(100) == 0)
        {
            int r = R.nextInt(19);
            switch(r)
            {
                case 0:
                    is = new ItemStack(TFCItems.SeedsWheat,1); break;
                case 1:
                    is = new ItemStack(TFCItems.SeedsMaize,1); break;
                case 2:
                    is = new ItemStack(TFCItems.SeedsTomato,1); break;
                case 3:
                    is = new ItemStack(TFCItems.SeedsBarley,1); break;
                case 4:
                    is = new ItemStack(TFCItems.SeedsRye,1); break;
                case 5:
                    is = new ItemStack(TFCItems.SeedsOat,1); break;
                case 6:
                    is = new ItemStack(TFCItems.SeedsRice,1); break;
                case 7:
                    is = new ItemStack(TFCItems.SeedsPotato,1); break;
                case 8:
                    is = new ItemStack(TFCItems.SeedsOnion,1); break;
                case 9:
                    is = new ItemStack(TFCItems.SeedsCabbage,1); break;
                case 10:
                    is = new ItemStack(TFCItems.SeedsGarlic,1); break;
                case 11:
                    is = new ItemStack(TFCItems.SeedsCarrot,1); break;
                case 12:
                    is = new ItemStack(TFCItems.SeedsYellowBellPepper,1); break;
                case 13:
                    is = new ItemStack(TFCItems.SeedsRedBellPepper,1); break;
                case 14:
                    is = new ItemStack(TFCItems.SeedsSoybean,1); break;
                case 15:
                    is = new ItemStack(TFCItems.SeedsGreenbean,1); break;
                case 16:
                    is = new ItemStack(TFCItems.SeedsSquash,1); break;
            }
        }
        return is;
    }
}
