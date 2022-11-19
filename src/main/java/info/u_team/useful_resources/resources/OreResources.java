package info.u_team.useful_resources.resources;

import static info.u_team.useful_resources.resource.common.CommonResourceBuilder.basicOre;
import static info.u_team.useful_resources.resource.common.CommonResourceBuilder.noExisting;

import info.u_team.useful_resources.api.resource.Resources;
import info.u_team.useful_resources.resource.Resource;
import net.minecraft.world.item.Rarity;

@Resources
public class OreResources {
	
	public static final Resource SILVER = basicOre("silver", 0xFFFF00, Rarity.EPIC, noExisting()).baseDestroyTime(2).build();
	
	private OreResources() {
	}
}
