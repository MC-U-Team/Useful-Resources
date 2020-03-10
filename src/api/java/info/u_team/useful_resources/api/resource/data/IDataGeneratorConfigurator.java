package info.u_team.useful_resources.api.resource.data;

import java.util.Map;

import info.u_team.useful_resources.api.worldgen.WorldGenFeature;

public interface IDataGeneratorConfigurator {
	
	OreType getOreType();
	
	Map<String, WorldGenFeature> getWorldGeneration();
	
}
