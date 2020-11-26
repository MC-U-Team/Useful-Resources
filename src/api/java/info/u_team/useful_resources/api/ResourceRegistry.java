package info.u_team.useful_resources.api;

import java.util.*;
import java.util.function.Consumer;

import com.google.common.collect.Iterables;

import info.u_team.useful_resources.api.resource.IResource;

public class ResourceRegistry {
	
	private static final List<IResource> RESOURCES = new ArrayList<>();
	
	public static <T extends IResource> T register(T resource) {
		if (RESOURCES.stream().anyMatch(registeredResources -> registeredResources.getName().equals(resource.getName()))) {
			throw new IllegalStateException("Cannot register a resource with the same name twice.");
		}
		RESOURCES.add(resource);
		return resource;
	}
	
	public static List<IResource> getResources() {
		return Collections.unmodifiableList(RESOURCES);
	}
	
	public static void forEach(Consumer<? super IResource> action) {
		RESOURCES.forEach(action);
	}
	
	public static IResource get(String name) {
		return Iterables.tryFind(RESOURCES, resource -> resource.getName().equals(name)).orNull();
	}
	
}
