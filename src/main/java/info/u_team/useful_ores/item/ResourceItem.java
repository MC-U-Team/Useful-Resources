package info.u_team.useful_ores.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_ores.api.IResource;
import info.u_team.useful_ores.init.UsefulOresItemGroups;

public class ResourceItem extends UItem {
	
	public ResourceItem(String type, IResource resource) {
		super(resource.getName() + "_" + type, UsefulOresItemGroups.GROUP, new Properties());
	}
	
}
