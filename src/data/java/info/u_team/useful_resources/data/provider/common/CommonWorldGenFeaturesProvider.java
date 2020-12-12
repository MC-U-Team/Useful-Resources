package info.u_team.useful_resources.data.provider.common;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.data.DirectoryCache;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public abstract class CommonWorldGenFeaturesProvider extends CommonProvider {
	
	private final Map<String, WorldGenFeatures> data;
	
	public CommonWorldGenFeaturesProvider(GenerationData data) {
		super(data);
		this.data = new HashMap<>();
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		addFeatures();
		data.forEach((name, feature) -> {
			try {
				final JsonElement element = JsonOps.INSTANCE.withEncoder(WorldGenFeatures.CODEC).apply(feature).result().orElseThrow(IllegalStateException::new);
				write(cache, element, path.resolve("world_generation").resolve(name + ".json"));
			} catch (final IOException ex) {
				LOGGER.error(marker, "Could not write data.", ex);
			}
		});
	}
	
	protected abstract void addFeatures();
	
	@Override
	public String getName() {
		return "Worldgen-Features";
	}
	
	protected void addFeature(String path, IWorldGenFeatures feature) {
		data.put(path, new WorldGenFeatures(feature.getFilters(), feature.getFeatures()) {
			
			@Override
			public WorldGenFeatures addFeature(Decoration decoration, Supplier<ConfiguredFeature<?, ?>> feature) {
				throw new IllegalStateException();
			}
		});
	}
	
}
