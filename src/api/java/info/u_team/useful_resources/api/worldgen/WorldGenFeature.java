package info.u_team.useful_resources.api.worldgen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import info.u_team.useful_resources.api.list.TypeList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	private final TypeList<Category> categories;
	private final TypeList<Biome> biomes;
	
	private final ConfiguredFeature<?, ?> feature;
	
	public WorldGenFeature(TypeList<Category> categories, TypeList<Biome> biomes, ConfiguredFeature<?, ?> feature) {
		this.categories = categories;
		this.biomes = biomes;
		this.feature = feature;
	}
	
	@Override
	public TypeList<Category> getCategories() {
		return categories;
	}
	
	@Override
	public TypeList<Biome> getBiomes() {
		return biomes;
	}
	
	@Override
	public ConfiguredFeature<?, ?> getFeature() {
		return feature;
	}
}
