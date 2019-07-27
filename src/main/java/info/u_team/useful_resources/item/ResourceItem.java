package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.api.config.IResourceItemConfig;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceItem extends UItem {
	
	public ResourceItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(resource.getName() + "_" + type.getName(), UsefulResourcesItemGroups.GROUP, new Properties().rarity(config.getRarity()));
	}
	
}
