package pnb.warpstone;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import pnb.utils.services.BlockService;
import pnb.utils.services.ItemService;
import pnb.warpstone.guice.WarpStoneModule;
import pnb.warpstone.proxy.CommonProxy;

@Mod(
	modid = "warpstone",
	name = "Rox's Warp Stones",
	version = "v1"
)

public class Core {
	@SidedProxy (
		clientSide = "rox.warpstone.proxy.ClientProxy",
		serverSide = "rox.warpstone.proxy.CommonProxy"
	)
	//This MUST remain static. If it's not static then it causes a crash.
	private static CommonProxy proxy;
	
	private ItemService is;
	private BlockService bs;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// Initialize Sponge
		Injector i = Guice.createInjector(new WarpStoneModule());
		proxy.setInjector(i);
        is = i.getInstance(ItemService.class);
        bs = i.getInstance(BlockService.class);
		
		//Initialize and register our custom items
		is.registerItem("warp_stone", new ItemWarpStone());
		//Initialize and register our custom blocks
		bs.registerBlock("warp_pillar", new BlockWarpPillar());
		
		//Sets the item's model and texture.
		//This is done after the item has been initialized.
		proxy.setRenders();
		//Sets the crafting recipes.
		GameRegistry.addShapelessRecipe(
				//This is the output, the Warp Stone.
				new ItemStack(is.getItem("warp_stone")),
				
				//These are the ingredients for it.
				Items.ender_pearl,
				Items.redstone,
				Items.glowstone_dust);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//There's nothing to do here. This remains blank.
	}
}
