package info.u_team.useful_resources.api.worldgen;

import java.util.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.list.ListType;

public class FilterLists {
	
	public static final Codec<FilterLists> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				CategoryTypeList.CODEC.fieldOf("categories").forGetter(FilterLists::getCategories), //
				BiomeTypeList.CODEC.fieldOf("biomes").forGetter(FilterLists::getBiomes) //
		).apply(instance, FilterLists::new);
	});
	
	public static FilterLists create(ListType categoriesListType, ListType biomesListType) {
		return new FilterLists(CategoryTypeList.create(categoriesListType), BiomeTypeList.create(biomesListType));
	}
	
	private final CategoryTypeList categories;
	private final BiomeTypeList biomes;
	
	private FilterLists(CategoryTypeList categories, BiomeTypeList biomes) {
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
