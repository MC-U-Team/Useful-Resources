package info.u_team.useful_resources.api.type;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IResourceType<T extends IForgeRegistryEntry<T>> {
	
	String getName();
	
	boolean hasUnifyTag();
	
	INamedTag<T> getUnifyTag();
	
	boolean hasTag();
	
	INamedTag<T> getTag(IResource resource);
	
}
