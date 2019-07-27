package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceItems extends IUArrayRegistryType<Item> {
	
	IResource getResource();
	
	Item getItem(IResourceItemTypes type);
	
	boolean hasItem(IResourceItemTypes type);
	
	default Tag<Item> getTag(IResourceItemTypes type) {
		return type.getTag(this);
	}
	
	default Tag<Item> getUnifyTag(IResourceItemTypes type) {
		return type.getUnifyTag();
	}
}
