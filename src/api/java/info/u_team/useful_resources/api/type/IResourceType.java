package info.u_team.useful_resources.api.type;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceType {
	
	String getName();
	
	Tag<Item> getUnifyTag();
	
	Tag<Item> getTag(IResource resource);
	
}
