package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.tool.USwordItem;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceSwordItem extends USwordItem {
	
	public ResourceSwordItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(resource.getName() + "_" + type.getName(), UsefulResourcesItemGroups.GROUP, new Properties().rarity(config.getRarity()), resource.getItems().getToolMaterial());
	}
	
}
