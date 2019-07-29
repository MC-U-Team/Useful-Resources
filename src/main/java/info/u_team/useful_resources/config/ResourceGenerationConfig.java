package info.u_team.useful_resources.config;

import java.util.stream.*;

import com.google.gson.annotations.SerializedName;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.api.resource.config.generation.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.*;

public class ResourceGenerationConfig implements IResourceGenerationConfig {
	
	private final static Category[] OVERWORLD_BLACKLIST = new Category[] { Category.NETHER, Category.THEEND }; // Blacklist
	private final static Category[] NETHER_WHITELIST = new Category[] { Category.NETHER }; // Whitelist
	
	public static ResourceGenerationConfig createRangeOverworld(int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return createRange(ListType.BLACKLIST, OVERWORLD_BLACKLIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NATURAL_STONE, veinSize, count, bottomOffset, topOffset, maximum);
	}
	
	public static ResourceGenerationConfig createRangeNether(int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return createRange(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST, new Biome[0], FillerBlockType.NETHERRACK, veinSize, count, bottomOffset, topOffset, maximum);
	}
	
	public static ResourceGenerationConfig createRange(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlock, int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return create(categoriesType, categories, biomesType, biomes, fillerBlock, veinSize, new ResourceGenerationType(new CountRangeConfig(count, bottomOffset, topOffset, maximum)));
	}
	
	public static ResourceGenerationConfig createDepthAverageOverworld(Biome[] biomes, int veinSize, int count, int baseline, int spread) {
		return createDepthAverage(ListType.BLACKLIST, OVERWORLD_BLACKLIST, ListType.BLACKLIST, biomes, FillerBlockType.NATURAL_STONE, veinSize, count, baseline, spread);
	}
	
	public static ResourceGenerationConfig createDepthAverageNether(Biome[] biomes, int veinSize, int count, int baseline, int spread) {
		return createDepthAverage(ListType.WHITELIST, NETHER_WHITELIST, ListType.BLACKLIST, biomes, FillerBlockType.NETHERRACK, veinSize, count, baseline, spread);
	}
	
	public static ResourceGenerationConfig createDepthAverage(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlock, int veinSize, int count, int baseline, int spread) {
		return create(categoriesType, categories, biomesType, biomes, fillerBlock, veinSize, new ResourceGenerationType(new DepthAverageConfig(count, baseline, spread)));
	}
	
	public static ResourceGenerationConfig create(ListType categoriesType, Category[] categories, ListType biomesType, Biome[] biomes, FillerBlockType fillerBlock, int veinSize, ResourceGenerationType type) {
		return new ResourceGenerationConfig(true, new TypedList<>(categoriesType, Stream.of(categories).collect(Collectors.toList())), new TypedList<>(biomesType, Stream.of(biomes).collect(Collectors.toList())), fillerBlock, veinSize, type);
	}
	
	private final boolean enabled;
	private final TypedList<Category> categories;
	private final TypedList<Biome> biomes;
	@SerializedName("filler_block")
	private final FillerBlockType fillerBlock;
	@SerializedName("vein_size")
	private final int veinSize;
	@SerializedName("generation")
	private final IResourceGenerationTypeConfig type;
	
	public ResourceGenerationConfig(boolean enabled, TypedList<Category> categories, TypedList<Biome> biomes, FillerBlockType fillerBlock, int veinSize, ResourceGenerationType type) {
		this.enabled = enabled;
		this.categories = categories;
		this.biomes = biomes;
		this.fillerBlock = fillerBlock;
		this.veinSize = veinSize;
		this.type = type;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public TypedList<Category> getBiomeCategories() {
		return categories;
	}
	
	@Override
	public TypedList<Biome> getBiomes() {
		return biomes;
	}
	
	@Override
	public FillerBlockType getFillerBlock() {
		return fillerBlock;
	}
	
	@Override
	public int getVeinSize() {
		return veinSize;
	}
	
	@Override
	public IResourceGenerationTypeConfig getType() {
		return type;
	}
	
	public void validate() {
		if (categories == null || biomes == null || fillerBlock == null || type == null) {
			throw new IllegalStateException("Generation config is not valid. Some values are null.");
		}
	}
}
