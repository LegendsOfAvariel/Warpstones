package pnb.warpstone;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class ItemWarpStone extends Item{
	
	public ItemWarpStone() {
		//Some settings for the item.
		super();
		//The name of the item when referred to by commands and the .lang file.
		this.setUnlocalizedName("warp_stone");
		//Stack size, this item will take damage as it's used, so keep this at 1.
		this.setMaxStackSize(1);
		//The item's durability. The amount of times an item can be used is always
		//1 more than it's durability. Here, the Warp Stone can be used 16 times.
		this.setMaxDamage(15);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
		//Assigns a creative tab. Since this mod only has one item, I'm assigning
		//it to a vanilla tab; Tools.
		this.setCreativeTab(CreativeTabs.tabTools);
		
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		//Only bind if the player is sneaking.
		if(player.isSneaking()){
			//Create the tag if it doesn't exist.
			if(stack.getTagCompound() == null){
				stack.setTagCompound(new NBTTagCompound());
			}
			
			//Stores the dimension ID, X, Y and Z coordinates to the tag.
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger("dim", player.dimension);
			nbt.setInteger("x", pos.getX());
			nbt.setInteger("y", pos.getY());
			nbt.setInteger("z", pos.getZ());
			//Assigns the tag to the item.
			stack.getTagCompound().setTag("coords", nbt);
			
			//Tells the player that the warp stone was bound successfully.
			if(!player.worldObj.isRemote){
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Location bound!"));
			}
		}
		//Return statement is necessary. Returning false works just fine.
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		//Make sure the player isn't sneaking so that they don't teleport when they want to bind the stone.
		if(!player.isSneaking()){
			//Make sure that the tag exists, otherwise things break.
			if(stack.getTagCompound() != null){
				//Make sure that "coords" exists in the tag, since that's what I'm pulling from.
				//I don't think I have to do this here, though it's a good habit to form for later.
				if(stack.getTagCompound().hasKey("coords")){
					//Reassigning the tag to "nbt".
					NBTTagCompound nbt = (NBTTagCompound) stack.getTagCompound().getTag("coords");
					//Setting up the integers here too.
					int dim = nbt.getInteger("dim");
					int x = nbt.getInteger("x");
					int y = nbt.getInteger("y");
					int z = nbt.getInteger("z");
					
					//Checking if the player is in the same dimension that the stone is bound to.
					if(dim == player.worldObj.provider.getDimensionId()){
						//Teleporting the player to the marked position, X and Z incremented by half a block so
						//that the player teleports onto the center of the block, and increasing the Y by 1 so
						//that the player is put on top of the bound block.
						player.setPositionAndUpdate(x+0.5, y+1, z+0.5);
						//Plays the enderman teleport sound on the bound location after the player is warped.
						world.playSoundEffect(x+0.5, y+0.5, z+0.5, "mob.endermen.portal", 0.5F, 0.8F);
						//Take 1 off of the warp stone's durability.
						stack.damageItem(1, player);
					}
					//If the player isn't in the same dimension as the stone is bound to then
					//tell the player that they can't warp across dimensions.
					else{
						if(!player.worldObj.isRemote){
							player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You can't warp across dimensions!"));
						}
					}
				}
			}
			//If the player hasn't bound the stone yet, tell them that they need to bind it
			//before they can use it to teleport.
			else{
				//I used two tellPlayer() methods here since this message spans two lines.
				//The color resets whenever a string goes onto a new line for some odd reason.
				//I can't fix it since it's a problem with Forge or Minecraft so this is a work-around.
				if(!player.worldObj.isRemote){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "You haven't bound a location yet! Sneak and use the item on a"));
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "block to bind it to that block."));
				}
			}
		}
		//Returning the stack is necessary.
		return stack;
	}
	
	//Tells the client to add info to the item's tooltip.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced){
		//Check to see if the tag is in place.
		if(stack.getTagCompound() != null){
			//Check if the tag "coords" exists.
			if(stack.getTagCompound().hasKey("coords")){
				//Once again, reassigning the tag to "nbt" and setting the integers.
				NBTTagCompound nbt = (NBTTagCompound) stack.getTagCompound().getTag("coords");
				int dim = nbt.getInteger("dim");
				int x = nbt.getInteger("x");
				int y = nbt.getInteger("y");
				int z = nbt.getInteger("z");
				
				//Gets current damage.
				int usesCurrent = stack.getItemDamage();
				//Gets max damage.
				int usesMax = stack.getMaxDamage() + 1;
				//Subtracts the current damage from max damage to show how many uses remain later.
				int uses = usesMax - usesCurrent;
				
				//Empty string.
				String dimname = null;
				
				//Assigning something to "dimname" based on the dimension ID.
				//If another mod adds a new dimension with a fixed ID, add it to this list.
				switch(dim){
				case -1:
					dimname = "The Nether";
					break;
				case 0:
					dimname = "The Overworld";
					break;
				case 1:
					dimname = "The End";
					break;
				default:
					dimname = null;
				}
				
				//If "dimname" was given something in the switch and is no longer empty, I use
				//"dimname" as the dimension in the tooltip.
				if(dimname != null){
					tooltip.add(EnumChatFormatting.GOLD + "Dimension: " + EnumChatFormatting.RESET + dimname);
					tooltip.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.RESET + x +
								EnumChatFormatting.GOLD + " Y: " + EnumChatFormatting.RESET + y +
								EnumChatFormatting.GOLD + " Z: " + EnumChatFormatting.RESET + z);
					tooltip.add(EnumChatFormatting.GOLD + "Uses Left: " + EnumChatFormatting.RESET + uses);
				}
				//If "dimname" is still empty, I display the dimension's ID instead.
				else{
					tooltip.add(EnumChatFormatting.RED + "Unknown Dimension! " +
								EnumChatFormatting.GOLD + "ID: " + EnumChatFormatting.RESET + dim);
					tooltip.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.RESET + x +
								EnumChatFormatting.GOLD + " Y: " + EnumChatFormatting.RESET + y +
								EnumChatFormatting.GOLD + " Z: " + EnumChatFormatting.RESET + z);
					tooltip.add(EnumChatFormatting.GOLD + "Uses Left: " + EnumChatFormatting.RESET + uses);
				}
			}
		}
		//If there's no tag, say that there's no location bound on the Warp Stone's tooltip.
		else{
			tooltip.add(EnumChatFormatting.GOLD + "No location bound.");
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	//This gives it the enchant glow if true.
	public boolean hasEffect(ItemStack stack){
		//If the tag exists and it contains "coords", this returns true.
		if(stack.getTagCompound() != null){
			if(stack.getTagCompound().hasKey("coords")){
				return true;
			}
		}
		//Otherwise, it returns false.
		return false;
	}
}
