package info.u_team.useful_resources.api.stack;

import net.minecraft.item.*;

public class ItemCountProvider implements IItemCountProvider {
	
	private final ItemStack stack;
	
	public ItemCountProvider(ItemStack stack) {
		this.stack = stack;
	}
	
	@Override
	public Item asItem() {
		return stack.getItem();
	}
	
	@Override
	public int getCount() {
		return stack.getCount();
	}
	
	@Override
	public ItemStack getItemStack() {
		return stack.copy();
	}
	
}
