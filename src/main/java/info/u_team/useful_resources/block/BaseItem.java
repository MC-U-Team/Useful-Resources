package info.u_team.useful_resources.block;

import info.u_team.u_team_core.item.UItem;
import info.u_team.useful_resources.init.UsefulResourcesCreativeTabs;
import net.minecraft.world.item.Rarity;

public class BaseItem extends UItem {
	
	public BaseItem(Rarity rarity) {
		super(UsefulResourcesCreativeTabs.TAB, new Properties().rarity(rarity));
	}
	
}
