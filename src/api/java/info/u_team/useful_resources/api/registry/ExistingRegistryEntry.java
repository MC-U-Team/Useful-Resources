package info.u_team.useful_resources.api.registry;

import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;

public class ExistingRegistryEntry<T> extends RegistryEntry<T> {
	
	public static <E> RegistryEntry<E> createExisting(ResourceLocation name, Supplier<E> entry) {
		return new ExistingRegistryEntry<E>(name, entry::get);
	}
	
	protected ExistingRegistryEntry(ResourceLocation name, Supplier<T> entry) {
		super(name, entry);
	}
	
}
