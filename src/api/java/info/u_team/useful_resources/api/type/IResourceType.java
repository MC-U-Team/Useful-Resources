package info.u_team.useful_resources.api.type;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.tags.Tag;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IResourceType<T extends IForgeRegistryEntry<T>> {
	
	String getName();
	
	boolean hasUnifyTag();
	
	Tag<T> getUnifyTag();
	
	boolean hasTag();
	
	Tag<T> getTag(IResource resource);
	
}
