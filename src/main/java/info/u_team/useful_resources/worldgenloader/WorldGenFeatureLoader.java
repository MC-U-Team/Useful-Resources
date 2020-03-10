package info.u_team.useful_resources.worldgenloader;

import java.util.*;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.*;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;

import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldGenFeatureLoader extends JsonReloadListener {
	
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	
	private static final Map<Biome, List<Pair<Decoration, ConfiguredFeature<?, ?>>>> loaded = new HashMap<>();
	
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
		
		map.forEach((resourceLocation, json) -> {
			final WorldGenFeature feature = WorldGenFeature.deserialize(new Dynamic<>(JsonOps.INSTANCE, json));
			
			ForgeRegistries.BIOMES.forEach(biome -> {
				if (!feature.getCategories().testWithType(biome.getCategory()) || !feature.getBiomes().testWithType(biome)) {
					return;
				}
				loaded.computeIfAbsent(biome, $ -> new ArrayList<>()).add(Pair.of(feature.getDecoration(), feature.getFeature()));
			});
		});
		
		System.out.println(loaded);
		
		loaded.forEach((biome, list) -> {
			list.forEach(pair -> {
				final Decoration decoration = pair.getKey();
				final ConfiguredFeature<?, ?> configuredFeature = pair.getValue();
				biome.addFeature(decoration, configuredFeature);
			});
		});
	}
	
}
