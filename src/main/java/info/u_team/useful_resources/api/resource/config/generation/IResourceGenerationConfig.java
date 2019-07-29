package info.u_team.useful_resources.api.resource.config.generation;

import info.u_team.useful_resources.api.TypedList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public interface IResourceGenerationConfig {
	
	boolean isEnabled();
	
	TypedList<Category> getBiomeCategories();
	
	TypedList<Biome> getBiomes();
	
	int getVeinSize();
	
	IResourceGenerationTypeConfig getType();
	
}
