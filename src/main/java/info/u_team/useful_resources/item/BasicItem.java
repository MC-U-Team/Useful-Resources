package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.item.Rarity;

public class BasicItem extends UItem {
	
	public BasicItem(Rarity rarity) {
		super(UsefulResourcesItemGroups.GROUP, new Properties().rarity(rarity));
	}
	
}
