package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.tool.UAxeItem;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceAxeItem extends UAxeItem {
	
	public ResourceAxeItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(resource.getName() + "_" + type.getName(), UsefulResourcesItemGroups.GROUP, new Properties().rarity(config.getRarity()), resource.getItems().getToolMaterial());
	}
	
}
