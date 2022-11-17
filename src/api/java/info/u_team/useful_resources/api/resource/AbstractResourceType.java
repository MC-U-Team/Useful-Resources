package info.u_team.useful_resources.api.resource;

import net.minecraft.tags.TagKey;

public interface AbstractResourceType<T> {
	
	String getName();
	
	boolean hasUnifyTag();
	
	TagKey<T> getUnifyTag();
	
	boolean hasTag();
	
	TagKey<T> getTag(AbstractResource resource);
	
}
