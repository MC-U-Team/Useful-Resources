package info.u_team.useful_ores.init;

import java.util.List;

import info.u_team.useful_ores.api.*;
import info.u_team.useful_ores.api.IGeneratable.*;
import info.u_team.useful_ores.config.CommonConfig;
import net.minecraft.block.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulOresWorldGeneration {
	
	public static void setup() {
		final CommonConfig config = CommonConfig.getInstance();
		if (!config.worldGenerationEnabled.get()) {
			return;
		}
		ForgeRegistries.BIOMES.getValues().forEach(biome -> {
			addResource(biome, config.copper, UsefulOresBlocks.COPPER.getOre(), UsefulOresBlocks.COPPER.getNetherOre());
		});
	}
	
	private static void addResource(Biome biome, IResourceConfig resource, Block ore, Block netherOre) {
		addGeneratable(biome, ore.getDefaultState(), FillerBlockType.NATURAL_STONE, resource.getOreGeneratable().get());
		addGeneratable(biome, netherOre.getDefaultState(), FillerBlockType.NETHERRACK, resource.getNetherOreGeneratable().get());
	}
	
	private static void addGeneratable(Biome biome, BlockState state, FillerBlockType fillerType, IGeneratable generatable) {
		if (!generatable.isEnabled()) {
			return;
		}
		final ListType biomeCategoryListType = generatable.getBiomeCategoryListType();
		final List<Category> biomeCategories = generatable.getBiomeCategories();
		if (biomeCategoryListType == ListType.BLACKLIST && biomeCategories.contains(biome.getCategory()) || biomeCategoryListType == ListType.WHITELIST && !biomeCategories.contains(biome.getCategory())) {
			return;
		}
		
		final ListType biomeListType = generatable.getBiomeListType();
		final List<Biome> biomes = generatable.getBiomes();
		if (biomeListType == ListType.BLACKLIST && biomes.contains(biome) || biomeListType == ListType.WHITELIST && !biomes.contains(biome)) {
			return;
		}
		
		if (generatable.getGenerationConfig() == GenerationConfig.COUNT_RANGE) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(fillerType, state, generatable.getVeinSize()), Placement.COUNT_RANGE, generatable.getCountRangeConfig()));
		} else if (generatable.getGenerationConfig() == GenerationConfig.COUNT_DEPTH_AVERAGE) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(fillerType, state, generatable.getVeinSize()), Placement.COUNT_DEPTH_AVERAGE, generatable.getDepthAverageConfig()));
		}
	}
	
}
