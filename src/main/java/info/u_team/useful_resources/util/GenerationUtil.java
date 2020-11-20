package info.u_team.useful_resources.util;

import java.util.*;
import java.util.function.UnaryOperator;

import info.u_team.useful_resources.api.list.ListType;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;

public class GenerationUtil {
	
	private static final TypeLists OVERWORLD_BLACKLIST = new TypeLists(CategoryTypeList.create(ListType.BLACKLIST).add(Category.NETHER, Category.THEEND, Category.NONE), BiomeTypeList.create(ListType.BLACKLIST));
	private static final TypeLists NETHER_WHITELIST = new TypeLists(CategoryTypeList.create(ListType.WHITELIST).add(Category.NETHER), BiomeTypeList.create(ListType.BLACKLIST));
	
	/*
	 * public static WorldGenFeatures createOreFeatureRangeOverworld(BlockState state, int size, int count, int
	 * bottomOffset, int topOffset, int maximum) { return createOreFeatureRange(ListType.BLACKLIST, OVERWORLD_BLACKLIST,
	 * ListType.BLACKLIST, new Biome[0], FillerBlockType.field_241882_a, state, size, count, bottomOffset, topOffset,
	 * maximum); }
	 * 
	 * public static WorldGenFeatures createOreFeatureRangeNether(BlockState state, int size, int count, int bottomOffset,
	 * int topOffset, int maximum) { return createOreFeatureRange(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST,
	 * new Biome[0], FillerBlockType.field_241883_b, state, size, count, bottomOffset, topOffset, maximum); }
	 * 
	 * public static WorldGenFeatures createOreFeatureRange(ListType categoriesType, Category[] categories, ListType
	 * biomesType, Biome[] biomes, RuleTest fillerBlockType, BlockState state, int size, int count, int bottomOffset, int
	 * topOffset, int maximum) { return createOreFeature(categoriesType, categories, biomesType, biomes, fillerBlockType,
	 * state, size, Placement.field_242907_l.configure(new TopSolidRangeConfig(bottomOffset, topOffset, maximum))); }
	 * 
	 * public static WorldGenFeatures createOreFeatureDeathAverageOverworld(BlockState state, int size, int count, int
	 * baseline, int spread) { return createOreFeatureDeathAverage(ListType.BLACKLIST, OVERWORLD_BLACKLIST,
	 * ListType.BLACKLIST, new Biome[0], FillerBlockType.field_241882_a, state, size, count, baseline, spread); }
	 * 
	 * public static WorldGenFeatures createOreFeatureDeathAverageNether(BlockState state, int size, int count, int
	 * baseline, int spread) { return createOreFeatureDeathAverage(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST,
	 * new Biome[0], FillerBlockType.field_241883_b, state, size, count, baseline, spread); }
	 * 
	 * public static WorldGenFeatures createOreFeatureDeathAverage(ListType categoriesType, Category[] categories, ListType
	 * biomesType, Biome[] biomes, RuleTest fillerBlockType, BlockState state, int size, int count, int baseline, int
	 * spread) { return createOreFeature(categoriesType, categories, biomesType, biomes, fillerBlockType, state, size,
	 * Placement.field_242910_o.configure(new DepthAverageConfig(baseline, spread))); }
	 */
	
	// public static WorldGenFeatures createOreFeature(TypeLists typeLists, RuleTest fillerBlockType, BlockState state, int
	// size, UnaryOperator<ConfiguredFeature<?, ?>> decoratable, Optional<List<ConfiguredPlacement<?>>> extraPlacements) {
	// }
	
	public static WorldGenFeatures createOreFeature(TypeLists typeLists, RuleTest fillerBlockType, BlockState state, int size, UnaryOperator<ConfiguredFeature<?, ?>> decoratable, Optional<List<ConfiguredPlacement<?>>> extraPlacements) {
		final ConfiguredFeature<?, ?> feature = decoratable.apply(Feature.ORE.withConfiguration(new OreFeatureConfig(fillerBlockType, state, size)));
		if (extraPlacements.isPresent()) {
			extraPlacements.get().forEach(feature::withPlacement);
		}
		return createFeature(typeLists, Decoration.UNDERGROUND_ORES, feature);
	}
	
	public static WorldGenFeatures createFeature(TypeLists typeLists, Decoration decoration, ConfiguredFeature<?, ?> feature) {
		return WorldGenFeatures.create(typeLists.getCategories(), typeLists.getBiomes()).addFeature(decoration, () -> feature);
	}
	
	public static class TypeLists {
		
		private final CategoryTypeList categories;
		private final BiomeTypeList biomes;
		
		public TypeLists(CategoryTypeList categories, BiomeTypeList biomes) {
			this.categories = categories;
			this.biomes = biomes;
		}
		
		public CategoryTypeList getCategories() {
			return categories;
		}
		
		public BiomeTypeList getBiomes() {
			return biomes;
		}
	}
}
