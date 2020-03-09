package info.u_team.useful_resources.api.worldgen;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public interface IWorldGenFeature {
	
	CategoryTypeList getCategories();
	
	BiomeTypeList getBiomes();
	
	ConfiguredFeature<?, ?> getFeature();
	
}
