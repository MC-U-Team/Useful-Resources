package info.u_team.useful_resources.api.resource.config.generation;

import info.u_team.useful_resources.api.TypedArrayList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;

public interface IResourceGenerationConfig {
	
	boolean isEnabled();
	
	TypedArrayList<Category> getBiomeCategories();
	
	TypedArrayList<Biome> getBiomes();
	
	int getVeinSize();
	
	IResourceGenerationTypeConfig getType();
	
}
