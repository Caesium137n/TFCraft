package TFC.Items;

import java.util.List;
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
import net.minecraftforge.common.ForgeDirection;
import TFC.TFCBlocks;
import TFC.Core.AnvilReq;
import TFC.Enums.EnumSize;
import TFC.Enums.EnumWeight;
import TFC.TileEntities.TileEntityTerraAnvil;
import TFC.TileEntities.TileEntityToolRack;

public class ItemToolRack extends ItemTerraBlock
{
	String[] Names = {"Oak","Aspen","Birch","Chestnut","Douglas Fir","Hickory","Maple","Ash","Pine",
            "Sequoia","Spruce","Sycamore","White Cedar","White Elm","Willow","Kapok"};
	
	public ItemToolRack(int id) {
		super(id);
		this.setIconIndex(16);
		this.hasSubtypes = true;
        this.setMaxDamage(0);
	}

	@Override
	public EnumSize getSize() {
		return EnumSize.HUGE;
	}
	
	 @Override
		public EnumWeight getWeight() {
			return EnumWeight.LIGHT;
		}

	@Override
	public int getMetadata(int i) 
	{		
		return i;
	}
	@Override
	public int getIconFromDamage(int i)
    {
		return 16+i;
    }

	@Override
	public String getTextureFile()
	{
		return "/bioxx/terrasprites.png";
	}
	
	@Override
    public String getItemNameIS(ItemStack itemstack) 
    {
        String s = new StringBuilder().append(super.getItemName()).append(".").append(Names[itemstack.getItemDamage()]).toString();
        return s;
    }

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		int dir = MathHelper.floor_double((double)(player.rotationYaw * 4F / 360F) + 0.5D) & 3;
		if(!world.isRemote && world.isBlockNormalCube(x, y, z) && world.isBlockOpaqueCube(x, y, z))
		{
			int id = TFCBlocks.ToolRack.blockID;
			if(side == 2 && world.getBlockId(x, y, z-1) == 0)
			{
				world.setBlockAndMetadataWithNotify(x, y, z-1, id, 0);
				world.markBlockForUpdate(x, y, z-1);
				if(world.getBlockTileEntity(x, y, z-1) != null)
				{
					TileEntityToolRack te = (TileEntityToolRack)world.getBlockTileEntity(x, y, z-1);
					te.woodType = (byte) itemstack.getItemDamage();
					te.broadcastPacketInRange(te.createUpdatePacket());
				}
				player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
			}
			else if(side == 3 && world.getBlockId(x, y, z+1) == 0)
			{
				world.setBlockAndMetadataWithNotify(x, y, z+1, id, 2);
				world.markBlockForUpdate(x, y, z+1);
				if(world.getBlockTileEntity(x, y, z+1) != null)
				{
					TileEntityToolRack te = (TileEntityToolRack)world.getBlockTileEntity(x, y, z+1);
					te.woodType = (byte) itemstack.getItemDamage();
					te.broadcastPacketInRange(te.createUpdatePacket());
				}
				player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
			}
			else if(side == 4 && world.getBlockId(x-1, y, z) == 0)
			{
				world.setBlockAndMetadataWithNotify(x-1, y, z, id, 3);
				world.markBlockForUpdate(x-1, y, z);
				if(world.getBlockTileEntity(x-1, y, z) != null)
				{
					TileEntityToolRack te = (TileEntityToolRack)world.getBlockTileEntity(x-1, y, z);
					te.woodType = (byte) itemstack.getItemDamage();
					te.broadcastPacketInRange(te.createUpdatePacket());
				}
				player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
			}
			else if(side == 5 && world.getBlockId(x+1, y, z) == 0)
			{
				world.setBlockAndMetadataWithNotify(x+1, y, z, id, 1);
				world.markBlockForUpdate(x+1, y, z);
				if(world.getBlockTileEntity(x+1, y, z) != null)
				{
					TileEntityToolRack te = (TileEntityToolRack)world.getBlockTileEntity(x+1, y, z);
					te.woodType = (byte) itemstack.getItemDamage();
					te.broadcastPacketInRange(te.createUpdatePacket());
				}
				player.inventory.mainInventory[player.inventory.currentItem].stackSize--;
			}
			return true;
		}

		return false;
	}
	
	

}
