package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.IResourceBlocks;
import info.u_team.useful_resources.api.resource.config.generation.*;
import info.u_team.useful_resources.config.CommonConfig;
import info.u_team.useful_resources.type.*;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesWorldGeneration {
	
	@SubscribeEvent
	public static void register(FMLCommonSetupEvent event) {
		final CommonConfig config = CommonConfig.getInstance();
		if (!config.worldGenerationEnabled.get()) {
			return;
		}
		ForgeRegistries.BIOMES.getValues().forEach(biome -> Resources.getValues().forEach(resource -> addResource(biome, resource.getBlocks())));
	}
	
	private static void addResource(Biome biome, IResourceBlocks blocks) {
		ResourceBlockTypes.VALUES.stream().filter(blocks::hasBlock).filter(blocks::hasWorldGeneration).forEach(type -> addGeneratable(biome, blocks.getBlock(type).getDefaultState(), blocks.getWorldGeneration(type)));
	}
	
	private static void addGeneratable(Biome biome, BlockState state, IResourceGenerationConfig config) {
		if (!config.isEnabled()) {
			return;
		}
		
		if (!config.getBiomeCategories().testWithType(biome.getCategory()) || !config.getBiomes().testWithType(biome)) {
			return;
		}
		final IResourceGenerationTypeConfig type = config.getType();
		if (type.getGenerationType() == GenerationType.COUNT_RANGE) {
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(config.getFillerBlock(), state, config.getVeinSize()), Placement.COUNT_RANGE, type.getCountRangeConfig()));
		} else {
			biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(config.getFillerBlock(), state, config.getVeinSize()), Placement.COUNT_DEPTH_AVERAGE, type.getDepthAverageConfig()));
		}
	}
}
