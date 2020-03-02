package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.item.*;

public class BasicItem extends UItem {
	
	public BasicItem(String name, Rarity rarity) {
		super(name, UsefulResourcesItemGroups.GROUP, new Properties().rarity(rarity));
	}
	
}
