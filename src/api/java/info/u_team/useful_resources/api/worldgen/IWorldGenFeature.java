package info.u_team.useful_resources.api.worldgen;

import info.u_team.useful_resources.api.list.TypeList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.*;

public interface IWorldGenFeature<C extends IFeatureConfig> {
	
	Feature<C> getFeature();
	
	TypeList<Category> getCategories();
	
	TypeList<Biome> getBiomes();
	
}
