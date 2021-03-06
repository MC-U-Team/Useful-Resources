package info.u_team.useful_resources.api.stack;

import java.util.function.Supplier;

import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;

public class ItemCountProvider implements IItemCountProvider {
	
	public static IItemCountProvider of(Supplier<IResource> supplier, ItemResourceType type) {
		return of(supplier, type, 1);
	}
	
	public static IItemCountProvider of(Supplier<IResource> supplier, ItemResourceType type, int count) {
		return of(() -> supplier.get().getItems().get(type).get(), count);
	}
	
	public static IItemCountProvider of(Supplier<IResource> supplier, BlockResourceType type) {
		return of(supplier, type, 1);
	}
	
	public static IItemCountProvider of(Supplier<IResource> supplier, BlockResourceType type, int count) {
		return of(() -> supplier.get().getBlocks().get(type).get(), count);
	}
	
	public static IItemCountProvider of(Supplier<? extends IItemProvider> supplier) {
		return of(supplier, 1);
	}
	
	public static IItemCountProvider of(Supplier<? extends IItemProvider> supplier, int count) {
		return new ItemCountProvider(supplier, count);
	}
	
	private final Supplier<? extends IItemProvider> supplier;
	private final int count;
	
	protected ItemCountProvider(Supplier<? extends IItemProvider> supplier, int count) {
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
