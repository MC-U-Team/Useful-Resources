package info.u_team.useful_resources.resource;

import java.util.function.Supplier;

import info.u_team.useful_resources.api.ResourceCreator;
import info.u_team.useful_resources.api.ResourceType;

public class BasicResourceCreator<T> implements ResourceCreator<T> {
	
	private final Supplier<T> type;
	
	public BasicResourceCreator(Supplier<T> type) {
		this.type = type;
	}
	
	@Override
	public Supplier<T> create() {
		return type;
	}
	
	@Override
	public String createRegistryName(ResourceType<T> type, String resourceName) {
		return type.getName() + "_" + resourceName;
	}
	
}
