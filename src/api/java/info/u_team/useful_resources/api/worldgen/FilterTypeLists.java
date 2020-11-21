package info.u_team.useful_resources.api.worldgen;

import java.util.Objects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class FilterTypeLists {
	
	public static final Codec<FilterTypeLists> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(FilterTypeLists::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(FilterTypeLists::getBiomes) //
		).apply(instance, FilterTypeLists::new);
	});
	
	public static FilterTypeLists create(CategoryTypeList categories, BiomeTypeList biomes) {
		return new FilterTypeLists(categories, biomes);
	}
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	protected FilterTypeLists(CategoryTypeList categories, BiomeTypeList biomes) {
		this.categories = Objects.requireNonNull(categories);
		this.biomes = Objects.requireNonNull(biomes);
	}
	
	public CategoryTypeList getCategories() {
		return categories;
	}
	
	public BiomeTypeList getBiomes() {
		return biomes;
	}
}
