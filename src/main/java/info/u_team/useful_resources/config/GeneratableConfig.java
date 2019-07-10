package info.u_team.useful_resources.config;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import org.apache.logging.log4j.*;

import com.google.gson.*;

import info.u_team.useful_resources.api.IGeneratable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneratableConfig implements IGeneratable {
	
	private final static Category[] OVERWORLD_BLACKLIST = new Category[] { Category.NETHER, Category.THEEND }; // Blacklist
	private final static Category[] NETHER_WHITELIST = new Category[] { Category.NETHER }; // Whitelist
	
	public static GeneratableConfig createRangeOverworld(int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return createRange(ListType.BLACKLIST, OVERWORLD_BLACKLIST, veinSize, count, bottomOffset, topOffset, maximum);
	}
	
	public static GeneratableConfig createRangeNether(int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return createRange(ListType.WHITELIST, NETHER_WHITELIST, veinSize, count, bottomOffset, topOffset, maximum);
	}
	
	public static GeneratableConfig createRange(ListType biomeCategoryListType, Category[] biomeCategories, int veinSize, int count, int bottomOffset, int topOffset, int maximum) {
		return new GeneratableConfig(true, biomeCategoryListType, biomeCategories, ListType.BLACKLIST, new Biome[] {}, veinSize, GenerationConfig.COUNT_RANGE, new CountRangeConfig(count, bottomOffset, topOffset, maximum), null);
	}
	
	public static GeneratableConfig createDepthAverageOverworld(int veinSize, int count, int baseline, int spread) {
		return createDepthAverage(ListType.BLACKLIST, OVERWORLD_BLACKLIST, veinSize, count, baseline, spread);
	}
	
	public static GeneratableConfig createDepthAverageNether(int veinSize, int count, int baseline, int spread) {
		return createDepthAverage(ListType.WHITELIST, NETHER_WHITELIST, veinSize, count, baseline, spread);
	}
	
	public static GeneratableConfig createDepthAverage(ListType biomeCategoryListType, Category[] biomeCategories, int veinSize, int count, int baseline, int spread) {
		return new GeneratableConfig(true, biomeCategoryListType, biomeCategories, ListType.BLACKLIST, new Biome[] {}, veinSize, GenerationConfig.COUNT_DEPTH_AVERAGE, null, new DepthAverageConfig(count, baseline, spread));
	}
	
	private final boolean enabled;
	
	private final ListType biomeCategoryListType;
	private final List<Category> biomeCategories;
	private final ListType biomeListType;
	private final List<Biome> biomes;
	
	private final int veinSize;
	private final GenerationConfig type;
	private final CountRangeConfig countRangeConfig;
	private final DepthAverageConfig depthAverageConfig;
	
	public GeneratableConfig(boolean enabled, ListType biomeCategoryListType, Category[] biomeCategories, ListType biomeListType, Biome[] biomes, int veinSize, GenerationConfig type, CountRangeConfig countRangeConfig, DepthAverageConfig depthAverageConfig) {
		this(enabled, biomeCategoryListType, Arrays.asList(biomeCategories), biomeListType, Arrays.asList(biomes), veinSize, type, countRangeConfig, depthAverageConfig);
	}
	
	public GeneratableConfig(boolean enabled, ListType biomeCategoryListType, List<Category> biomeCategories, ListType biomeListType, List<Biome> biomes, int veinSize, GenerationConfig type, CountRangeConfig countRangeConfig, DepthAverageConfig depthAverageConfig) {
		this.enabled = enabled;
		this.biomeCategoryListType = biomeCategoryListType;
		this.biomeCategories = biomeCategories;
		this.biomeListType = biomeListType;
		this.biomes = biomes;
		this.veinSize = veinSize;
		this.type = type;
		this.countRangeConfig = countRangeConfig;
		this.depthAverageConfig = depthAverageConfig;
	}
	
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public ListType getBiomeCategoryListType() {
		return biomeCategoryListType;
	}
	
	@Override
	public List<Category> getBiomeCategories() {
		return biomeCategories;
	}
	
	@Override
	public ListType getBiomeListType() {
		return biomeListType;
	}
	
	@Override
	public List<Biome> getBiomes() {
		return biomes;
	}
	
	@Override
	public int getVeinSize() {
		return veinSize;
	}
	
	@Override
	public GenerationConfig getGenerationConfig() {
		return type;
	}
	
	@Override
	public CountRangeConfig getCountRangeConfig() {
		return countRangeConfig;
	}
	
	@Override
	public DepthAverageConfig getDepthAverageConfig() {
		return depthAverageConfig;
	}
	
	public static class Serializer implements JsonSerializer<GeneratableConfig>, JsonDeserializer<GeneratableConfig> {
		
		private final Logger logger = LogManager.getLogger();
		
		@Override
		public JsonElement serialize(GeneratableConfig config, Type typeOfSrc, JsonSerializationContext context) {
			
			final JsonObject object = new JsonObject();
			
			object.addProperty("enabled", config.isEnabled());
			
			final JsonObject biomeCategoryObject = new JsonObject();
			biomeCategoryObject.addProperty("list_type", config.getBiomeCategoryListType().getName());
			final JsonArray biomeCategoryList = new JsonArray();
			config.getBiomeCategories().stream().map(Biome.Category::getName).forEach(biomeCategoryList::add);
			biomeCategoryObject.add("list", biomeCategoryList);
			object.add("biome_category", biomeCategoryObject);
			
			final JsonObject biomeObject = new JsonObject();
			biomeObject.addProperty("list_type", config.getBiomeListType().getName());
			final JsonArray biomeList = new JsonArray();
			config.getBiomes().stream().map(Biome::getRegistryName).map(ResourceLocation::toString).forEach(biomeList::add);
			biomeObject.add("list", biomeList);
			object.add("biome", biomeObject);
			
			object.addProperty("vein_size", config.getVeinSize());
			
			final JsonObject placementObject = new JsonObject();
			placementObject.addProperty("placement_type", config.getGenerationConfig().getName());
			if (config.getGenerationConfig() == GenerationConfig.COUNT_RANGE) {
				placementObject.addProperty("count", config.getCountRangeConfig().count);
				placementObject.addProperty("bottom_offset", config.getCountRangeConfig().bottomOffset);
				placementObject.addProperty("top_offset", config.getCountRangeConfig().topOffset);
				placementObject.addProperty("maximum", config.getCountRangeConfig().maximum);
			} else {
				placementObject.addProperty("count", config.getDepthAverageConfig().count);
				placementObject.addProperty("baseline", config.getDepthAverageConfig().baseline);
				placementObject.addProperty("spread", config.getDepthAverageConfig().spread);
			}
			object.add("placement", placementObject);
			
			return object;
		}
		
		@Override
		public GeneratableConfig deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			final JsonObject object = json.getAsJsonObject();
			
			final boolean enabled = object.get("enabled").getAsBoolean();
			
			final JsonObject biomeCategoryObject = object.get("biome_category").getAsJsonObject();
			final ListType biomeCategoryListType = ListType.byName(biomeCategoryObject.get("list_type").getAsString());
			final List<Biome.Category> biomeCategories = new ArrayList<>();
			biomeCategoryObject.get("list").getAsJsonArray().forEach(element -> {
				final Biome.Category category = NAMES_TO_CATEGORY.get(element.getAsString());
				if (category == null) {
					logger.warn("Biome category " + element.getAsString() + " could not be found.");
				} else {
					biomeCategories.add(category);
				}
			});
			
			final JsonObject biomeObject = object.get("biome").getAsJsonObject();
			final ListType biomeListType = ListType.byName(biomeObject.get("list_type").getAsString());
			final List<Biome> biomes = new ArrayList<>();
			biomeObject.get("list").getAsJsonArray().forEach(element -> {
				final Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(element.getAsString()));
				if (biome == null) {
					logger.warn("Biome " + element.getAsString() + " could not be found.");
				} else {
					biomes.add(biome);
				}
			});
			
			final int veinSize = object.get("vein_size").getAsInt();
			
			final JsonObject placementObject = object.get("placement").getAsJsonObject();
			final GenerationConfig type = GenerationConfig.byName(placementObject.get("placement_type").getAsString());
			
			final CountRangeConfig countRangeConfig;
			final DepthAverageConfig depthAverageConfig;
			
			try {
				if (type == GenerationConfig.COUNT_RANGE) {
					countRangeConfig = new CountRangeConfig(placementObject.get("count").getAsInt(), placementObject.get("bottom_offset").getAsInt(), placementObject.get("top_offset").getAsInt(), placementObject.get("maximum").getAsInt());
					depthAverageConfig = null;
				} else {
					depthAverageConfig = new DepthAverageConfig(placementObject.get("count").getAsInt(), placementObject.get("baseline").getAsInt(), placementObject.get("spread").getAsInt());
					countRangeConfig = null;
				}
			} catch (Exception ex) {
				logger.error("Could not parase generation config. Maybe wrong arguments defined?");
				throw ex;
			}
			
			return new GeneratableConfig(enabled, biomeCategoryListType, biomeCategories, biomeListType, biomes, veinSize, type, countRangeConfig, depthAverageConfig);
		}
		
		private static final Map<String, Biome.Category> NAMES_TO_CATEGORY = new HashMap<>();
		static {
			Stream.of(Biome.Category.values()).forEach(category -> NAMES_TO_CATEGORY.put(category.getName(), category));
		}
	}
}
