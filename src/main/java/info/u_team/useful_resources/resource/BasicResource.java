package info.u_team.useful_resources.resource;

import java.util.Map;
import java.util.Set;

import info.u_team.useful_resources.api.Resource;
import info.u_team.useful_resources.api.ResourceType;

public class BasicResource implements Resource {
	
	private final String name;
	private final Set<ResourceType<?>> entries;
	private final Map<ResourceType<?>, Object> existing;
	
	protected BasicResource(String name, Set<ResourceType<?>> entries, Map<ResourceType<?>, Object> existing) {
		this.name = name;
		this.entries = entries;
		this.existing = existing;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
}
