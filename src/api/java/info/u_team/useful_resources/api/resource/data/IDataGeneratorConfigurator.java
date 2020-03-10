package info.u_team.useful_resources.api.resource.data;

import java.util.List;

import info.u_team.useful_resources.api.worldgen.WorldGenFeature;

public interface IDataGeneratorConfigurator {
	
	OreType getOreType();
	
	List<WorldGenFeature> getWorldGeneration();
	
}
