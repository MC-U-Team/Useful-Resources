package info.u_team.useful_resources.api.type;

import java.util.Map;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

interface CacheResourceType<T extends IForgeRegistryEntry<T>> extends IResourceType<T> {
	
	@Override
	public default boolean hasUnifyTag() {
		return getTagName() != null;
	}
	
	@Override
	public default Tag<T> getUnifyTag() {
		return getTag(new ResourceLocation("forge", getTagName()));
	}
	
	@Override
	public default boolean hasTag() {
		return getTagName() != null;
	}
	
	@Override
	public default Tag<T> getTag(IResource resource) {
		return getTag(new ResourceLocation("forge", getTagName() + "/" + resource.getName()));
	}
	
	default Tag<T> getTag(ResourceLocation location) {
		if (location == null) {
			return null;
		}
		return getCache().computeIfAbsent(location, this::createTag);
	}
	
	public String getTagName();
	
	public Tag<T> createTag(ResourceLocation location);
	
	public Map<ResourceLocation, Tag<T>> getCache();
	
}
