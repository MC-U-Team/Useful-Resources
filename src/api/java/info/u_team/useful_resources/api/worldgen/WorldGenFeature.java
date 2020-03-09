package info.u_team.useful_resources.api.worldgen;

import info.u_team.useful_resources.api.list.TypeList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	private final ConfiguredFeature<?, ?> feature;
	
	private final TypeList<Category> categories;
	private final TypeList<Biome> biomes;
	
	public WorldGenFeature(ConfiguredFeature<?, ?> feature, TypeList<Category> categories, TypeList<Biome> biomes) {
		this.feature = feature;
		this.categories = categories;
		this.biomes = biomes;
	}
	
	@Override
	public ConfiguredFeature<?, ?> getFeature() {
		return feature;
	}
	
	@Override
	public TypeList<Category> getCategories() {
		return categories;
	}
	
	@Override
	public TypeList<Biome> getBiomes() {
		return biomes;
	}
	
}
