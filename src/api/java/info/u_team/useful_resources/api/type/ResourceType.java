package info.u_team.useful_resources.api.type;

import java.util.*;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

abstract class ResourceType<T extends IForgeRegistryEntry<T>> implements IResourceType<T> {
	
	private final Map<ResourceLocation, Tag<T>> cache = new HashMap<>();
	
	protected final String tagName;
	
	public ResourceType(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean hasUnifyTag() {
		return tagName != null;
	}
	
	@Override
	public Tag<T> getUnifyTag() {
		return getTag(new ResourceLocation("forge", tagName));
	}
	
	@Override
	public boolean hasTag() {
		return tagName != null;
	}
	
	@Override
	public Tag<T> getTag(IResource resource) {
		return getTag(new ResourceLocation("forge", tagName + "/" + resource.getName()));
	}
	
	private Tag<T> getTag(ResourceLocation location) {
		if (tagName == null) {
			return null;
		}
		return cache.computeIfAbsent(new ResourceLocation("forge", tagName), this::createTag);
	}
	
	public abstract Tag<T> createTag(ResourceLocation location);
}
