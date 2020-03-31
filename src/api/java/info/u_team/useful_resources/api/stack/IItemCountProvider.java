package info.u_team.useful_resources.api.stack;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public interface IItemCountProvider extends IItemProvider {
	
	int getCount();
	
	default ItemStack getItemStack() {
		return new ItemStack(asItem(), getCount());
	}
	
}
