package info.u_team.useful_resources.resource.common;

import info.u_team.useful_resources.resource.ResourceType;
import info.u_team.useful_resources.resource.ResourceTypeBuilder;
import net.minecraft.world.level.block.Block;

public class CommonResourceTypes {
	
	public static final ResourceType<Block> ORE = ResourceTypeBuilder.block("ore").build();
	
	private CommonResourceTypes() {
	}
	
}
