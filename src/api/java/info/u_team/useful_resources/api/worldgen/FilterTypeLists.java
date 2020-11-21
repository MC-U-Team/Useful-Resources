package info.u_team.useful_resources.api.worldgen;

import java.util.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.list.ListType;

public class FilterTypeLists {
	
	public static final Codec<FilterTypeLists> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(FilterTypeLists::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(FilterTypeLists::getBiomes) //
		).apply(instance, FilterTypeLists::new);
	});
	
	public static FilterTypeLists create(ListType categoriesListType, ListType biomesListType) {
		return new FilterTypeLists(CategoryTypeList.create(categoriesListType), BiomeTypeList.create(biomesListType));
	}
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private FilterTypeLists(CategoryTypeList categories, BiomeTypeList biomes) {
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
