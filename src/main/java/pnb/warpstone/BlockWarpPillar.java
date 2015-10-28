package pnb.warpstone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockWarpPillar extends Block{
	public BlockWarpPillar(){
        //The block's material. Since it's a pillar, I'm making it stone.
		super(Material.rock);
		//The block's technical name.
		this.setUnlocalizedName("warp_pillar");
		//Harvest level of the block. 0 = wood/gold, 1 = stone, 2 = iron, 3 = diamond.
		//Setting this extremely high so it's nearly impossible to break in survival.
		this.setHarvestLevel("pickaxe", 1000);
		//Hardness is the time it takes to mine. Obsidian is 50.
		this.setHardness(5000);
		//Resistance is for explosions. Obsidian is 2000.
		this.setResistance(5000);
		//Block hitbox. For reference, 1/16th of a block is 0.0625F
		this.setBlockBounds(0F, 0F, 0F, 1F, 1.1875F, 1F);
	}
	
	//Since this block isn't a full cube, we want to make sure that the game recognizes
	//it as such and renders it appropriately. The following two booleans will make this
	//block render in a similar fashion to glass.
	
	//Fixes culling.
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
		return false;
	}
	
	//Fixes shadows.
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(){
		return false;
	}
}
