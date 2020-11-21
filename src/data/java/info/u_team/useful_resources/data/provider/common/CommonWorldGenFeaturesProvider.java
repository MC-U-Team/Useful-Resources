package info.u_team.useful_resources.data.provider.common;

import java.io.IOException;
import java.util.*;

import com.mojang.serialization.JsonOps;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.worldgen.WorldGenFeatures;
import net.minecraft.data.DirectoryCache;

public abstract class CommonWorldGenFeaturesProvider extends CommonProvider {
	
	private final Map<String, WorldGenFeatures> data;
	
	public CommonWorldGenFeaturesProvider(GenerationData data) {
		super(data);
		this.data = new HashMap<>();
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		addFeatures();
		data.forEach((path, feature) -> {
			try {
				write(cache, feature.serialize(JsonOps.INSTANCE).getValue(), resolveModData().resolve("useful_resources").resolve("worldgen_feature").resolve(path + ".json"));
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
	
	protected void addFeature(String path, WorldGenFeatures feature) {
		data.put(path, feature);
	}
	
}
