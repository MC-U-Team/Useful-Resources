package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.armor.UChestplateItem;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceChestplateItem extends UChestplateItem {
	
	public ResourceChestplateItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(resource.getName(), UsefulResourcesItemGroups.GROUP, new Properties().rarity(config.getRarity()), resource.getItems().getArmorMaterial());
	}
	
}
