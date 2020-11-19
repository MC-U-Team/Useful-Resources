package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	public static final Codec<WorldGenFeature> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(IWorldGenFeature::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(IWorldGenFeature::getBiomes), //
				ConfiguredFeature.field_242764_c.fieldOf("feature").forGetter(IWorldGenFeature::getFeatures) //
		).apply(instance, WorldGenFeature::new);
	});
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	// private final Decoration decoration;
	
	private final List<Supplier<ConfiguredFeature<?, ?>>> features;
	
	public WorldGenFeature(CategoryTypeList categories, BiomeTypeList biomes, /* Decoration decoration, */ List<Supplier<ConfiguredFeature<?, ?>>> features) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
		// this.decoration = Objects.requireNonNull(decoration);
		this.features = Objects.requireNonNull(features);
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
		return null;
		// return decoration;
	}
	
	@Override
	public List<Supplier<ConfiguredFeature<?, ?>>> getFeatures() {
		return features;
	}
	
	// public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
	// return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("categories"),
	// categories.serialize(ops).getValue(), ops.createString("biomes"), biomes.serialize(ops).getValue(),
	// ops.createString("decoration"), ops.createString(decoration.getName()), ops.createString("feature"),
	// feature.serialize(ops).getValue())));
	// }
	//
	// public static <T> WorldGenFeature deserialize(Dynamic<T> ops) {
	// final CategoryTypeList categories = CategoryTypeList.deserialize(ops.get("categories").orElseEmptyMap());
	// final BiomeTypeList biomes = BiomeTypeList.deserialize(ops.get("biomes").orElseEmptyMap());
	// final Decoration decoration = Decoration.BY_NAME.getOrDefault(ops.get("decoration").asString(""),
	// Decoration.RAW_GENERATION);
	// final ConfiguredFeature<?, ?> feature = ConfiguredFeature.deserialize(ops.get("feature").orElseEmptyMap());
	// return new WorldGenFeature(categories, biomes, decoration, feature);
	// }
	
}
