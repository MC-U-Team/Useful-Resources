package info.u_team.useful_resources.api.worldgen;

import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final ConfiguredFeature<?, ?> feature;
	
	public WorldGenFeature(CategoryTypeList categories, BiomeTypeList biomes, ConfiguredFeature<?, ?> feature) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
		this.feature = Objects.requireNonNull(feature);
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
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("categories"), categories.serialize(ops).getValue(), ops.createString("biomes"), biomes.serialize(ops).getValue(), ops.createString("feature"), feature.serialize(ops).getValue())));
	}
	
	public static <T> WorldGenFeature deserialize(Dynamic<T> ops) {
		final CategoryTypeList categories = CategoryTypeList.deserialize(ops.get("categories").orElseEmptyMap());
		final BiomeTypeList biomes = BiomeTypeList.deserialize(ops.get("biomes").orElseEmptyMap());
		final ConfiguredFeature<?, ?> feature = ConfiguredFeature.deserialize(ops.get("biomes").orElseEmptyMap());
		return new WorldGenFeature(categories, biomes, feature);
	}
	
}
