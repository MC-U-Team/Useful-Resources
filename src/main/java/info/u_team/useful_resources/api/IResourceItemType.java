package info.u_team.useful_resources.api;

import info.u_team.useful_resources.api.config.IResourceItemConfig;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceItemType extends IResourceType {
	
	Tag<Item> getUnifyTag();
	
	Tag<Item> getTag(IResourceItems items);
	
	Item createItem(IResource resource, IResourceItemConfig config);
}