package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.ResourceCreator;
import info.u_team.useful_resources.api.ResourceType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class BasicResourceType<T> implements ResourceType<T> {
	
	private final String name;
	private final ResourceKey<? extends Registry<T>> registry;
	private final ResourceCreator<T> creator;
	
	public BasicResourceType(String name, ResourceKey<? extends Registry<T>> registry, ResourceCreator<T> creator) {
		this.name = name;
		this.registry = registry;
		this.creator = creator;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ResourceKey<? extends Registry<T>> getRegistry() {
		return registry;
	}
	
	@Override
	public ResourceCreator<T> getCreator() {
		return creator;
	}
	
}
