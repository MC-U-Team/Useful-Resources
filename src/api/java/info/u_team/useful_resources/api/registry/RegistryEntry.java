package info.u_team.useful_resources.api.registry;

import java.util.function.Supplier;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryEntry<T extends IForgeRegistryEntry<? super T>> implements Supplier<T> {
	
	public static <E extends IForgeRegistryEntry<? super E>> RegistryEntry<E> create(RegistryObject<E> object) {
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
	
}
