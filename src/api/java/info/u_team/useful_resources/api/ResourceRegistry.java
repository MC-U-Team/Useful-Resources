package info.u_team.useful_resources.api;

import java.util.*;

import com.google.common.collect.Iterables;

import info.u_team.useful_resources.api.type.BlockResourceType;
import net.minecraft.util.IItemProvider;

public class ResourceRegistry {
	
	private static final List<IResource> RESOURCES = new ArrayList<>();
	
	private static IItemProvider itemGroupItem;
	
	public static void register(IResource resource) {
		if (RESOURCES.stream().anyMatch(registeredResources -> registeredResources.getName().equals(resource.getName()))) {
			throw new IllegalStateException("Cannot register a resource with the same name twice.");
		}
		RESOURCES.add(resource);
	}
	
	public static List<IResource> getResources() {
		return Collections.unmodifiableList(RESOURCES);
	}
	
	public IResource get(String name) {
		return Iterables.tryFind(RESOURCES, resource -> resource.getName().equals(name)).orNull();
	}
	
	public static IItemProvider getItemGroupItem() {
		if (itemGroupItem == null) {
			itemGroupItem = RESOURCES.stream().filter(resource -> resource.getName().equals("copper")).map(resource -> resource.getBlocks().get(BlockResourceType.ORE)).findAny().orElseThrow(IllegalStateException::new);
		}
		return itemGroupItem;
	}
	
}
