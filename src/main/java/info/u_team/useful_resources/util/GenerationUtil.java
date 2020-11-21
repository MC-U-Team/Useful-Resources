package info.u_team.useful_resources.util;

import java.util.*;
import java.util.function.UnaryOperator;

import info.u_team.useful_resources.api.list.*;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.*;

public class GenerationUtil {
	
	private static final FilterTypeLists OVERWORLD_FILTER = FilterTypeLists.create(CategoryTypeList.create(ListType.BLACKLIST).add(Category.NETHER, Category.THEEND, Category.NONE), BiomeTypeList.create(ListType.BLACKLIST));
	private static final FilterTypeLists NETHER_FILTER = FilterTypeLists.create(CategoryTypeList.create(ListType.WHITELIST).add(Category.NETHER), BiomeTypeList.create(ListType.BLACKLIST));
	
	// ----------------------- TODO see of that works ------------------------------- //
	
	public static WorldGenFeatures createOreFeatureRangeOverworld(BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeatureRange(OVERWORLD_FILTER, FillerBlockType.BASE_STONE_OVERWORLD, state, size, count, bottomOffset, topOffset, maximum);
	}
	
	public static WorldGenFeatures createOreFeatureRangeNether(BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeatureRange(NETHER_FILTER, FillerBlockType.NETHERRACK, state, size, count, bottomOffset, topOffset, maximum);
	}
	
	public static WorldGenFeatures createOreFeatureRange(FilterTypeLists filterTypeLists, RuleTest fillerBlockType, BlockState state, int size, int count, int bottomOffset, int topOffset, int maximum) {
		return createOreFeature(filterTypeLists, fillerBlockType, state, size, feature -> feature.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(bottomOffset, topOffset, maximum))), Optional.empty());
	}
	
	public static WorldGenFeatures createOreFeatureDepthAverageOverworld(BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeatureDepthAverage(OVERWORLD_FILTER, FillerBlockType.BASE_STONE_OVERWORLD, state, size, count, baseline, spread);
	}

	public static WorldGenFeatures createOreFeatureDepthAverageNether(BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeatureDepthAverage(NETHER_FILTER, FillerBlockType.NETHERRACK, state, size, count, baseline, spread);
	}
	
	public static WorldGenFeatures createOreFeatureDepthAverage(FilterTypeLists filterTypeLists, RuleTest fillerBlockType, BlockState state, int size, int count, int baseline, int spread) {
		return createOreFeature(filterTypeLists, fillerBlockType, state, size, feature -> feature.withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(baseline, spread))), Optional.empty());
	}
	
	// ----------------------- ---------------------- ------------------------------- //
	
	public static WorldGenFeatures createOreFeature(FilterTypeLists filterTypeLists, RuleTest fillerBlockType, BlockState state, int size, UnaryOperator<ConfiguredFeature<?, ?>> decoratable, Optional<List<ConfiguredPlacement<?>>> extraPlacements) {
		final ConfiguredFeature<?, ?> feature = decoratable.apply(Feature.ORE.withConfiguration(new OreFeatureConfig(fillerBlockType, state, size)));
		if (extraPlacements.isPresent()) {
			extraPlacements.get().forEach(feature::withPlacement);
		}
		return createFeature(filterTypeLists, Decoration.UNDERGROUND_ORES, feature);
	}
	
	public static WorldGenFeatures createFeature(FilterTypeLists filterTypeLists, Decoration decoration, ConfiguredFeature<?, ?> feature) {
		return WorldGenFeatures.create(filterTypeLists).addFeature(decoration, () -> feature);
	}
	
}
