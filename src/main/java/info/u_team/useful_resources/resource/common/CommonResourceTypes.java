package info.u_team.useful_resources.resource.common;

import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.resource.ResourceTypeBuilder;
import net.minecraft.world.level.block.Block;

public class CommonResourceTypes {
	
	public static final ResourceTypeKey<Block> ORE = ResourceTypeBuilder.block("ore").build();
	
	private CommonResourceTypes() {
	}
	
}
