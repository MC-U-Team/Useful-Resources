package info.u_team.useful_resources.api.worldgen;
/*
import java.util.Objects;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.*;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final Decoration decoration;
	
	private final ConfiguredFeature<?, ?> feature;
	
	public WorldGenFeature(CategoryTypeList categories, BiomeTypeList biomes, Decoration decoration, ConfiguredFeature<?, ?> feature) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
		this.decoration = Objects.requireNonNull(decoration);
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
	public Decoration getDecoration() {
		return decoration;
	}
	
	@Override
	public ConfiguredFeature<?, ?> getFeature() {
		return feature;
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("categories"), categories.serialize(ops).getValue(), ops.createString("biomes"), biomes.serialize(ops).getValue(), ops.createString("decoration"), ops.createString(decoration.getName()), ops.createString("feature"), feature.serialize(ops).getValue())));
	}
	
	public static <T> WorldGenFeature deserialize(Dynamic<T> ops) {
		final CategoryTypeList categories = CategoryTypeList.deserialize(ops.get("categories").orElseEmptyMap());
		final BiomeTypeList biomes = BiomeTypeList.deserialize(ops.get("biomes").orElseEmptyMap());
		final Decoration decoration = Decoration.BY_NAME.getOrDefault(ops.get("decoration").asString(""), Decoration.RAW_GENERATION);
		final ConfiguredFeature<?, ?> feature = ConfiguredFeature.deserialize(ops.get("feature").orElseEmptyMap());
		return new WorldGenFeature(categories, biomes, decoration, feature);
	}
	
}*/
