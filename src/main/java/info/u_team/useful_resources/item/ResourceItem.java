package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.api.IResource;
import info.u_team.useful_resources.init.UsefulOresItemGroups;

public class ResourceItem extends UItem {
	
	public ResourceItem(String type, IResource resource) {
		super(resource.getName() + "_" + type, UsefulOresItemGroups.GROUP, new Properties());
	}
	
}
