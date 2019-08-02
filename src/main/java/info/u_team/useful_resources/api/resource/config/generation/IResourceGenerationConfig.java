package info.u_team.useful_resources.api.resource.config.generation;

import info.u_team.useful_resources.api.TypedList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;

public interface IResourceGenerationConfig {
	
	boolean isEnabled();
	
	TypedList<Category> getBiomeCategories();
	
	TypedList<Biome> getBiomes();
	
	FillerBlockType getFillerBlock();
	
	int getVeinSize();
	
	IResourceGenerationTypeConfig getType();
	
}
