package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeatures implements IWorldGenFeatures {
	
	public static final Codec<WorldGenFeatures> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(IWorldGenFeatures::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(IWorldGenFeatures::getBiomes), //
				ConfiguredFeature.field_242764_c.listOf().fieldOf("features").forGetter(IWorldGenFeatures::getFeatures) //
		).apply(instance, WorldGenFeatures::new);
	});
	
	public static WorldGenFeatures create(CategoryTypeList categories, BiomeTypeList biomes) {
		final List<List<Supplier<ConfiguredFeature<?, ?>>>> list = new ArrayList<>();
		while (list.size() < Decoration.values().length) {
			list.add(new ArrayList<>());
		}
		return new WorldGenFeatures(categories, biomes, list);
	}
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final List<List<Supplier<ConfiguredFeature<?, ?>>>> features;
	
	private WorldGenFeatures(CategoryTypeList categories, BiomeTypeList biomes, List<List<Supplier<ConfiguredFeature<?, ?>>>> features) {
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
	
	public WorldGenFeatures addFeature(Decoration decoration, Supplier<ConfiguredFeature<?, ?>> feature) {
		final int ordinal = decoration.ordinal();
		while (features.size() <= ordinal) {
			features.add(new ArrayList<>());
		}
		features.get(ordinal).add(feature);
		return this;
	}
}
