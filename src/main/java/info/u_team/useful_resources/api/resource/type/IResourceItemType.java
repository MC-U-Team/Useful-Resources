package info.u_team.useful_resources.api.resource.type;

import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceItemType extends IResourceType {
	
	Tag<Item> getUnifyTag();
	
	Tag<Item> getTag(IResourceItems items);
	
	Item createItem(IResource resource, IResourceItemConfig config);
}