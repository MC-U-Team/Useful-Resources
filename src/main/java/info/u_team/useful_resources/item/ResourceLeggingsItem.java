package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.armor.ULeggingsItem;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceLeggingsItem extends ULeggingsItem {
	
	public ResourceLeggingsItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(resource.getName(), UsefulResourcesItemGroups.GROUP, new Properties().rarity(config.getRarity()), resource.getItems().getArmorMaterial());
	}
	
}
