package TFC.Items;

import java.util.List;

import TFC.TFCBlocks;
import TFC.Core.AnvilReq;
import TFC.Core.Helper;
import TFC.Enums.EnumSize;
import TFC.Enums.EnumWeight;
import TFC.TileEntities.TileEntityTerraAnvil;
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
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;

public class ItemAnvil extends ItemTerra
{
	public int anvilId;
	public AnvilReq req;

	public ItemAnvil(int i, int id, AnvilReq Areq) 
	{
		super(i);
		anvilId = id;
		req = Areq;
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public EnumSize getSize() {
		return EnumSize.HUGE;
	}

	@Override
	public EnumWeight getWeight() {
		return EnumWeight.HEAVY;
	}
	
	@Override
	public int getMetadata(int i) 
	{		
		return i;
	}

	@Override
	public String getTextureFile()
	{
		return "/bioxx/terratools.png";
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int meta = MathHelper.floor_double((double)(player.rotationYaw * 4F / 360F) + 0.5D) & 3;
		if(!world.isRemote && side == 1 && world.isBlockNormalCube(x, y, z) && world.isBlockOpaqueCube(x, y, z) && 
				world.getBlockId(x, y+1, z) == 0)
		{
			byte byte0 = 0;
			if(meta == 0)//+z
			{
				byte0 = 8;
			}
			if(meta == 1)//-x
			{
				byte0 = 0;
			}
			if(meta == 2)//-z
			{
				byte0 = 8;
			}
			if(meta == 3)//+x
			{
				byte0 = 0;
			}
			int id = TFCBlocks.Anvil.blockID;
			id = req == AnvilReq.BISMUTHBRONZE || req == AnvilReq.BLACKBRONZE || req == AnvilReq.ROSEGOLD ? TFCBlocks.Anvil2.blockID : TFCBlocks.Anvil.blockID;
			world.setBlockAndMetadataWithNotify( x, y+1, z, id, byte0+anvilId);
            world.markBlockForUpdate(x, y+1, z);
			if(world.getBlockTileEntity(x, y+1, z) != null)
			{
			    TileEntityTerraAnvil te = (TileEntityTerraAnvil)world.getBlockTileEntity(x, y+1, z);
			    te.AnvilTier = req.Tier;
			}
			player.inventory.mainInventory[player.inventory.currentItem] = null;
			return true;
		}

		return false;
	}
}
