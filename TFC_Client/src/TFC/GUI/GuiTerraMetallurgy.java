package TFC.GUI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
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
import net.minecraft.src.ModLoader;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.village.*;
import net.minecraft.world.*;
import org.lwjgl.opengl.GL11;

import TFC.*;
import TFC.Containers.*;
import TFC.TileEntities.*;


public class GuiTerraMetallurgy extends GuiContainer
{
	private TileEntityTerraMetallurgy entityMetallurgy;


	public GuiTerraMetallurgy(InventoryPlayer inventoryplayer, TileEntityTerraMetallurgy tileentityMetallurgy, World world, int x, int y, int z)
	{
		super(new ContainerTerraMetallurgy(inventoryplayer,tileentityMetallurgy, world, x, y, z) );
		entityMetallurgy = tileentityMetallurgy;
		
	}
	
	public void initGui()
    {
		super.initGui();
		//guiLeft = (width - 208) / 2;
        //guiTop = guiTop - 9;
		
        //controlList.clear();
        
        //controlList.add(new GuiButton(0, guiLeft+22, guiTop+54, 42, 20, "Combine"));
        
    }
	
	protected void actionPerformed(GuiButton guibutton)
	{
		if(true)
		{
			if (guibutton.id == 0)
			{
				//AnvilEntity.actionLightHammer();
			}
		}
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		int r = mc.renderEngine.getTexture("/bioxx/gui_metallurgy.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
        mc.renderEngine.bindTexture(r);
        int w = (width - 176) / 2;
        int h = (height - 184) / 2;
        drawTexturedModalRect(w, h, 0, 0, 175, 183);
		
	}
	
	protected void drawGuiContainerForegroundLayer()
    {        
//		String Data0 = entityMetallurgy.MetalData[0] > 0 ? entityMetallurgy.MetalData[0].toString() + "x " : "";
//		String Data1 = entityMetallurgy.MetalData[1] > 0 ? entityMetallurgy.MetalData[1].toString() + "x " : "";
//		String Data2 = entityMetallurgy.MetalData[2] > 0 ? entityMetallurgy.MetalData[2].toString() + "x " : "";
//		String Data3 = entityMetallurgy.MetalData[3] > 0 ? entityMetallurgy.MetalData[3].toString() + "x " : "";
//		String Data4 = entityMetallurgy.MetalData[4] > 0 ? entityMetallurgy.MetalData[4].toString() + "x " : "";
//		String Data5 = entityMetallurgy.MetalData[5] > 0 ? entityMetallurgy.MetalData[5].toString() + "x " : "";
//		fontRenderer.drawString("Metal Contents:", 81, 9, 0xFFFFFF);
//		fontRenderer.drawString(Data0 + entityMetallurgy.MetalNames[0], 81, 19, 0x404040);
//		fontRenderer.drawString(Data1 + entityMetallurgy.MetalNames[1], 81, 29, 0x404040);
//		fontRenderer.drawString(Data2 + entityMetallurgy.MetalNames[2], 81, 39, 0x404040);
//		fontRenderer.drawString(Data3 + entityMetallurgy.MetalNames[3], 81, 49, 0x404040);
//		fontRenderer.drawString(Data4 + entityMetallurgy.MetalNames[4], 81, 59, 0x404040);
//		fontRenderer.drawString(Data5 + entityMetallurgy.MetalNames[5], 81, 69, 0x404040);
    }
	
	public void drawCenteredString(FontRenderer fontrenderer, String s, int i, int j, int k)
    {
        fontrenderer.drawString(s, i - fontrenderer.getStringWidth(s) / 2, j, k);
    }


}
