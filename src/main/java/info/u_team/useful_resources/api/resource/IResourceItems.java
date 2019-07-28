package info.u_team.useful_resources.api.resource;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceItems extends IUArrayRegistryType<Item> {
	
	IResource getResource();
	
	Item getItem(IResourceItemType type);
	
	boolean hasItem(IResourceItemType type);
	
	default Tag<Item> getTag(IResourceItemType type) {
		return type.getTag(this);
	}
	
	default Tag<Item> getUnifyTag(IResourceItemType type) {
		return type.getUnifyTag();
	}
}
