package info.u_team.useful_resources.api.registry;

import java.util.Objects;
import java.util.function.Supplier;

import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryEntry<T extends IForgeRegistryEntry<? super T>> implements Supplier<T> {
	
	public static <E extends IForgeRegistryEntry<? super E>> RegistryEntry<E> create(RegistryObject<E> object) {
		return new RegistryEntry<E>(object.getId(), object);
	}
	
	public static <E extends Block> RegistryEntry<E> create(BlockRegistryObject<E, BlockItem> object) {
		return new RegistryEntry<E>(object.getId(), object);
	}
	
	public static <E extends IForgeRegistryEntry<? super E>> RegistryEntry<E> create(E entry) {
		return new RegistryEntry<E>(entry.getRegistryName(), () -> entry);
	}
	
	private final ResourceLocation name;
	private final Supplier<T> entry;
	
	public RegistryEntry(ResourceLocation name, Supplier<T> entry) {
		this.name = name;
		this.entry = entry;
	}
	
	public ResourceLocation getName() {
		return name;
	}
	
	@Override
	public T get() {
		return entry.get();
	}
	
	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object instanceof RegistryEntry) {
			return Objects.equals(((RegistryEntry<?>) object).name, name);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}
	
}
