package info.u_team.useful_resources.api.stack;

import java.util.function.Supplier;

import net.minecraft.item.*;
import net.minecraft.util.IItemProvider;

public class ItemCountProvider implements IItemCountProvider {
	
	private final Supplier<IItemProvider> supplier;
	private final int count;
	
	public ItemCountProvider(Supplier<IItemProvider> supplier, int count) {
		this.supplier = supplier;
		this.count = count;
	}
	
	@Override
	public Item asItem() {
		return supplier.get().asItem();
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
}
