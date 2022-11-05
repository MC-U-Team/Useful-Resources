package info.u_team.useful_resources.resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import info.u_team.useful_resources.api.ResourceType;

public class ResourceBuilder {
	
	private final String name;
	private final Set<ResourceType<?>> entries;
	private final Map<ResourceType<?>, Object> existing;
	
	public ResourceBuilder(String name) {
		this.name = name;
		entries = new HashSet<>();
		existing = new HashMap<>();
	}
	
	public <T> ResourceBuilder add(ResourceType<T> type) {
		entries.add(type);
		return this;
	}
	
	public <T> ResourceBuilder existing(ResourceType<T> type, T entry) {
		existing.put(type, entry);
		return this;
	}
	
	// TODO add use method for easier creation. Like use(VanillaResource)
	
	public BasicResource build() {
		return ResourceRegistry.register(new BasicResource(name, entries, existing));
	}
	
}
