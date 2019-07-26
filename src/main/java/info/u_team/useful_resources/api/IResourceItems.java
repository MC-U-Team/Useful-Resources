package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceItems extends IUArrayRegistryType<Item> {
	
	IResource getResource();
	
	Item getItem(IItemResourceTypes type);
	
	boolean hasItem(IItemResourceTypes type);
	
	default Tag<Item> getTag(IItemResourceTypes type) {
		return type.getTag(this);
	}
	
	default Tag<Item> getUnifyTag(IItemResourceTypes type) {
		return type.getUnifyTag();
	}
}
