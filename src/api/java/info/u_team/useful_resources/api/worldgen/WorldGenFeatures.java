package info.u_team.useful_resources.api.worldgen;

import java.util.*;
import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class WorldGenFeatures implements IWorldGenFeatures {
	
	public static final Codec<WorldGenFeatures> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				FilterTypeLists.CODEC.fieldOf("filters").forGetter(IWorldGenFeatures::getFilters), //
				ConfiguredFeature.field_242764_c.listOf().fieldOf("features").forGetter(IWorldGenFeatures::getFeatures) //
		).apply(instance, WorldGenFeatures::new);
	});
	
	public static WorldGenFeatures create(FilterTypeLists filters) {
		final List<List<Supplier<ConfiguredFeature<?, ?>>>> list = new ArrayList<>();
		while (list.size() < Decoration.values().length) {
			list.add(new ArrayList<>());
		}
		return new WorldGenFeatures(filters, list);
	}
	
	private final FilterTypeLists filters;
	
	private final List<List<Supplier<ConfiguredFeature<?, ?>>>> features;
	
	private WorldGenFeatures(FilterTypeLists filters, List<List<Supplier<ConfiguredFeature<?, ?>>>> features) {
		this.filters = Objects.requireNonNull(filters);
		this.features = Objects.requireNonNull(features);
	}
	
	@Override
	public FilterTypeLists getFilters() {
		return filters;
	}
	
	@Override
	public List<List<Supplier<ConfiguredFeature<?, ?>>>> getFeatures() {
		return features;
	}
	
	public WorldGenFeatures addFeature(Decoration decoration, Supplier<ConfiguredFeature<?, ?>> feature) {
		final int ordinal = decoration.ordinal();
		while (features.size() <= ordinal) {
			features.add(new ArrayList<>());
		}
		features.get(ordinal).add(feature);
		return this;
	}
}
