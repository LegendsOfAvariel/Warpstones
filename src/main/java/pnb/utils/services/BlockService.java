package pnb.utils.services;

import java.util.HashMap;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Singleton
public class BlockService {
	//Our little collection of custom items for quick lookup.
	private final HashMap<String,Block> customBlocks;
	
	@Inject
	public BlockService() {
		customBlocks = new HashMap<String,Block>();
	}
	
	public void registerBlock(String name, Block block) {
		//Registering the item.
		GameRegistry.registerBlock(block, name);
		
		//Initialize our custom item
		customBlocks.put(name, block);
	}
	
	public Block getBlock(String name) {
		return customBlocks.get(name);
	}
}
