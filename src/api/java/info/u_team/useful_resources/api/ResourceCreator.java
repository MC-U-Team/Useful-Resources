package info.u_team.useful_resources.api;

import java.util.function.Supplier;

public interface ResourceCreator<T> {
	
	Supplier<T> create();
	
	String createRegistryName(ResourceType<T> type, String resourceName);
	
}
