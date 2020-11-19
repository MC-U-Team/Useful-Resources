package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.util.GeneralEnumCodec;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeature implements IWorldGenFeature {
	
	public static final Codec<WorldGenFeature> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(IWorldGenFeature::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(IWorldGenFeature::getBiomes), //
				GeneralEnumCodec.createCodec(Decoration.class).fieldOf("decoration").forGetter(IWorldGenFeature::getDecoration), //
				ConfiguredFeature.field_242764_c.fieldOf("feature").forGetter(IWorldGenFeature::getFeatures) //
		).apply(instance, WorldGenFeature::new);
	});
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private final Decoration decoration;
	
	private final List<Supplier<ConfiguredFeature<?, ?>>> features;
	
	public WorldGenFeature(CategoryTypeList categories, BiomeTypeList biomes, Decoration decoration, List<Supplier<ConfiguredFeature<?, ?>>> features) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
		this.decoration = Objects.requireNonNull(decoration);
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
		return decoration;
	}
	
	@Override
	public List<Supplier<ConfiguredFeature<?, ?>>> getFeatures() {
		return features;
	}
}
