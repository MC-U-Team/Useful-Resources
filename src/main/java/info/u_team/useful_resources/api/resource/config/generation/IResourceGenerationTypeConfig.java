package info.u_team.useful_resources.api.resource.config.generation;

import net.minecraft.world.gen.placement.*;

public interface IResourceGenerationTypeConfig {
	
	GenerationType getGenerationType();
	
	CountRangeConfig getCountRangeConfig();
	
	DepthAverageConfig getDepthAverageConfig();
	
}