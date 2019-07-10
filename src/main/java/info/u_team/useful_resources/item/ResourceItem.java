package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.api.IResource;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceItem extends UItem {
	
	public ResourceItem(String type, IResource resource) {
		super(resource.getName() + "_" + type, UsefulResourcesItemGroups.GROUP, new Properties());
	}
	
}
