package info.u_team.useful_resources.api;

import java.util.*;

public class ResourceRegistry {
	
	private static final List<IResource> RESOURCES = new ArrayList<>();
	
	public static void register(IResource resource) {
		if (RESOURCES.stream().anyMatch(registeredResources -> registeredResources.getName().equals(resource.getName()))) {
			throw new IllegalStateException("Cannot register a resource with the same name twice.");
		}
		RESOURCES.add(resource);
	}
	
	public static List<IResource> getResources() {
		return Collections.unmodifiableList(RESOURCES);
	}
	
}
