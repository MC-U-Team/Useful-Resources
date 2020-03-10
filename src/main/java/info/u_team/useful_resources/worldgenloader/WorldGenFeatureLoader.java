package info.u_team.useful_resources.worldgenloader;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.*;

import com.google.gson.*;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.FORGE)
public class WorldGenFeatureLoader extends JsonReloadListener {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final Map<Biome, List<Pair<Decoration, ConfiguredFeature<?, ?>>>> loaded = new HashMap<>();
	
	@SubscribeEvent
	public static void serverStart(FMLServerAboutToStartEvent event) {
		event.getServer().getResourceManager().addReloadListener(new WorldGenFeatureLoader());
	}
	
	public WorldGenFeatureLoader() {
		super(GSON, "useful_resources/worldgen_feature");
	}
	
	@Override
	protected void apply(Map<ResourceLocation, JsonObject> map, IResourceManager resourceManager, IProfiler profiler) {
		loaded.forEach((biome, list) -> {
			list.forEach(pair -> {
				final Decoration decoration = pair.getKey();
				final ConfiguredFeature<?, ?> configuredFeature = pair.getValue();
				if (!biome.getFeatures(decoration).remove(configuredFeature)) {
					throw new IllegalStateException("Remove of " + configuredFeature + " failed!");
				}
			});
		});
		loaded.clear();
		
		final AtomicInteger counter = new AtomicInteger();
		
		map.forEach((resourceLocation, json) -> {
			if (json.size() == 0) { // Do not load jsons that do not contain any values
				return;
			}
			
			final WorldGenFeature feature = WorldGenFeature.deserialize(new Dynamic<>(JsonOps.INSTANCE, json));
			
			counter.incrementAndGet();
			
			ForgeRegistries.BIOMES.forEach(biome -> {
				if (!feature.getCategories().testWithType(biome.getCategory()) || !feature.getBiomes().testWithType(biome)) {
					return;
				}
				loaded.computeIfAbsent(biome, $ -> new ArrayList<>()).add(Pair.of(feature.getDecoration(), feature.getFeature()));
			});
		});
		
		loaded.forEach((biome, list) -> {
			list.forEach(pair -> {
				final Decoration decoration = pair.getKey();
				final ConfiguredFeature<?, ?> configuredFeature = pair.getValue();
				biome.addFeature(decoration, configuredFeature);
			});
		});
		LOGGER.info("Loaded {} world gen features", counter.intValue());
	}
	
}
