package info.u_team.useful_resources.api.type;

import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceType {
	
	String getName();
	
	Tag<Item> getUnifyTag();
	
}
