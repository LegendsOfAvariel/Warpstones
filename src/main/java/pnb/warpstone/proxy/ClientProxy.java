package pnb.warpstone.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class ClientProxy extends CommonProxy{
	
	@Override
	//This method is run in the Core class.
	public void setRenders(){
		//Render items and blocks here using Methods.renderItem() and Methods.renderBlock().
		//This should only be client-side.
		
		//Rendering the Warp Stone.
		Item warpStone = is.getItem("warp_stone");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				warpStone, 
				0, 
				new ModelResourceLocation("warpstone:" + warpStone.getUnlocalizedName().substring(5), "inventory"));
		
		//Rendering the Warp Pillar
		Block warpPillar = bs.getBlock("warp_pillar");
		Item warpPillarItem = Item.getItemFromBlock(warpPillar);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(
				warpPillarItem,
				0,
				new ModelResourceLocation("warpstone:" + warpPillar.getUnlocalizedName().substring(5), "inventory"));
	}
}
