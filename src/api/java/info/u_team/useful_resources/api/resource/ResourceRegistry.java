package info.u_team.useful_resources.api.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public final class ResourceRegistry {
	
	private static final Collection<AbstractResource> RESOURCES = new ArrayList<>();
	
	public static synchronized <T extends AbstractResource> T register(T resource) {
		if (RESOURCES.stream().anyMatch(registeredResources -> registeredResources.getName().equals(resource.getName()))) {
			throw new IllegalStateException("Cannot register a resource with the same name twice");
		}
		RESOURCES.add(resource);
		return resource;
	}
	
	public static Collection<AbstractResource> getResources() {
		return Collections.unmodifiableCollection(RESOURCES);
	}
	
	public static void forEach(Consumer<? super AbstractResource> consumer) {
		getResources().forEach(consumer);
	}
	
	public static AbstractResource get(String name) {
		return RESOURCES.stream().filter(resource -> resource.getName().equals(name)).findFirst().orElse(null);
	}
	
	private ResourceRegistry() {
	}
	
}
