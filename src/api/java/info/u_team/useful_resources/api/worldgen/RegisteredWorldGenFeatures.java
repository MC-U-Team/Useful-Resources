package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RegisteredWorldGenFeatures implements IWorldGenFeatures {
	
	private final FilterTypeLists filters;
	private final List<List<Supplier<ConfiguredFeature<?, ?>>>> features;
	
	public RegisteredWorldGenFeatures(IWorldGenFeatures worldGenFeatures) {
		filters = worldGenFeatures.getFilters();
		features = new ArrayList<>();
	}
	
	public void addList(int decorationStageIndex) {
		features.add(decorationStageIndex, new ArrayList<>());
	}
	
	public void addFeature(int decorationStageIndex, int featureListIndex, ConfiguredFeature<?, ?> feature) {
		features.get(decorationStageIndex).add(featureListIndex, () -> feature);
	}
	
	@Override
	public FilterTypeLists getFilters() {
		return filters;
	}
	
	@Override
	public List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures() {
		return features;
	}
	
}
