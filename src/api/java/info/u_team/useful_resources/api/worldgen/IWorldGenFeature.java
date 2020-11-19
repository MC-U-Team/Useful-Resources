package info.u_team.useful_resources.api.worldgen;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public interface IWorldGenFeature {
	
	CategoryTypeList getCategories();
	
	BiomeTypeList getBiomes();
	
	List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures();
	
}
