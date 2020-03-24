package info.u_team.useful_resources.api.resource.data;

import java.util.Map;

import info.u_team.useful_resources.api.worldgen.WorldGenFeature;

public interface IDataGeneratorConfigurator {
	
	Map<String, WorldGenFeature> getWorldGeneration();
	
	Map<String, Object> getExtraProperties();
	
}
