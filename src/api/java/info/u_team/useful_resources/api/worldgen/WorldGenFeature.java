package info.u_team.useful_resources.api.worldgen;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final ConfiguredFeature<?, ?> feature;
	
	public WorldGenFeature(CategoryTypeList categories, BiomeTypeList biomes, ConfiguredFeature<?, ?> feature) {
		this.categories = categories;
		this.biomes = biomes;
		this.feature = feature;
	}
	
	@Override
	public CategoryTypeList getCategories() {
		return categories;
	}
	
	@Override
	public BiomeTypeList getBiomes() {
		return biomes;
	}
	
	@Override
	public ConfiguredFeature<?, ?> getFeature() {
		return feature;
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		ops.createList(categories.getList().stream().map(category -> ops.createString(category.getName())));
		ops.createList(biomes.getList().stream().map(biome -> ops.createString(biome.getRegistryName().toString())));
		
		ops.createMap(ImmutableMap.of(ops.createString("type"), ops.createString(categories.getType().getName())));
		
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("feature"), feature.serialize(ops).getValue())));
	}
}
