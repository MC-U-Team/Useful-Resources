package info.u_team.useful_resources.init;

import info.u_team.u_team_core.itemgroup.UItemGroup;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.type.*;

public class UsefulResourcesItemGroups {
	
	public static final UItemGroup GROUP = new UItemGroup(UsefulResourcesMod.MODID, "group", () -> Resources.COPPER.getBlocks().getBlock(ResourceBlockTypes.ORE));
	
}