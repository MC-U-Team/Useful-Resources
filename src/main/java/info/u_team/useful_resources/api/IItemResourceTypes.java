package info.u_team.useful_resources.api;

import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IItemResourceTypes {
	
	String getName();
	
	Tag<Item> getUnifyTag();
	
	Tag<Item> getTag(IResourceItems items);
	
	Item createItem(IResource resource);
}