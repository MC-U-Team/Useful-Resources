package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeatures implements IWorldGenFeatures {
	
	public static final Codec<WorldGenFeatures> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(IWorldGenFeatures::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(IWorldGenFeatures::getBiomes), //
				ConfiguredFeature.field_242764_c.listOf().fieldOf("features").forGetter(IWorldGenFeatures::getFeatures) //
		).apply(instance, WorldGenFeatures::new);
	});
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final List<List<Supplier<ConfiguredFeature<?, ?>>>> features;
	
	public WorldGenFeatures(CategoryTypeList categories, BiomeTypeList biomes, List<List<Supplier<ConfiguredFeature<?, ?>>>> features) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
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
	public List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures() {
		return features;
	}
}
