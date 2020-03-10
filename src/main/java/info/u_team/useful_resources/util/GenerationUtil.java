package info.u_team.useful_resources.util;

import java.util.Arrays;

import info.u_team.useful_resources.api.list.ListType;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.*;

public class GenerationUtil {
	
	private final static Category[] OVERWORLD_BLACKLIST = new Category[] { Category.NETHER, Category.THEEND }; // Blacklist
	private final static Category[] NETHER_WHITELIST = new Category[] { Category.NETHER }; // Whitelist
	
	public static WorldGenFeature createOreFeatureRangeOverworld(BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeatureRange(ListType.BLACKLIST, OVERWORLD_BLACKLIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NATURAL_STONE, state, size, count, bottomOffset, topOffset, maximum);
	}
	
	public static WorldGenFeature createOreFeatureRangeNether(BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeatureRange(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NETHERRACK, state, size, count, bottomOffset, topOffset, maximum);
	}
	
	public static WorldGenFeature createOreFeatureRange(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlockType, BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeature(categoriesType, categories, biomesType, biomes, fillerBlockType, state, size, Placement.COUNT_RANGE.configure(new CountRangeConfig(count, bottomOffset, topOffset, maximum)));
	}
	
	public static WorldGenFeature createOreFeatureDeathAverageOverworld(BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeatureDeathAverage(ListType.BLACKLIST, OVERWORLD_BLACKLIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NATURAL_STONE, state, size, count, baseline, spread);
	}
	
	public static WorldGenFeature createOreFeatureDeathAverageNether(BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeatureDeathAverage(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NETHERRACK, state, size, count, baseline, spread);
	}
	
	public static WorldGenFeature createOreFeatureDeathAverage(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlockType, BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeature(categoriesType, categories, biomesType, biomes, fillerBlockType, state, size, Placement.COUNT_DEPTH_AVERAGE.configure(new DepthAverageConfig(count, baseline, spread)));
	}
	
	public static WorldGenFeature createOreFeature(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlockType, BlockState state, int size, ConfiguredPlacement<?> placement) {
		return createFeature(categoriesType, categories, biomesType, biomes, Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(fillerBlockType, state, size)).withPlacement(placement));
	}
	
	public static WorldGenFeature createFeature(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, Decoration decoration, ConfiguredFeature<?, ?> configuredFeature) {
		return new WorldGenFeature(new CategoryTypeList(categoriesType, Arrays.asList(categories)), new BiomeTypeList(biomesType, Arrays.asList(biomes)), decoration, configuredFeature);
	}
	
}
