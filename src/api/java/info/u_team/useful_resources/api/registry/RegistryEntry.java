package info.u_team.useful_resources.api.registry;

import java.util.Objects;
import java.util.function.Supplier;

import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class RegistryEntry<T> implements Supplier<T> {
	
	public static <E> RegistryEntry<E> create(RegistryObject<E> object) {
		return new RegistryEntry<E>(object.getId(), object);
	}
	
	public static <E extends Block> RegistryEntry<E> create(BlockRegistryObject<E, BlockItem> object) {
		return new RegistryEntry<E>(object.getId(), object);
	}
	
	public static <E> RegistryEntry<E> create(ResourceLocation name, Supplier<E> entry) {
		return new RegistryEntry<E>(name, entry::get);
	}
	
	private final ResourceLocation name;
	private final Supplier<T> entry;
	
	protected RegistryEntry(ResourceLocation name, Supplier<T> entry) {
		this.name = name;
		this.entry = entry;
	}
	
	@Override
	public T get() {
		return entry.get();
	}
	
	public ResourceLocation getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof RegistryEntry<?> entry) {
			return Objects.equals(entry.name, name);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}
}
