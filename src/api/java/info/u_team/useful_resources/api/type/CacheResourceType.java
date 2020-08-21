package info.u_team.useful_resources.api.type;

import java.util.Map;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

interface CacheResourceType<T extends IForgeRegistryEntry<T>> extends IResourceType<T> {
	
	@Override
	public default boolean hasUnifyTag() {
		return getTagName() != null;
	}
	
	@Override
	public default INamedTag<T> getUnifyTag() {
		return getTag(new ResourceLocation("forge", getTagName()));
	}
	
	@Override
	public default boolean hasTag() {
		return getTagName() != null;
	}
	
	@Override
	public default INamedTag<T> getTag(IResource resource) {
		return getTag(new ResourceLocation("forge", getTagName() + "/" + resource.getName()));
	}
	
	default INamedTag<T> getTag(ResourceLocation location) {
		if (location == null) {
			return null;
		}
		return getCache().computeIfAbsent(location, this::createTag);
	}
	
	public String getTagName();
	
	public INamedTag<T> createTag(ResourceLocation location);
	
	public Map<ResourceLocation, INamedTag<T>> getCache();
	
}
