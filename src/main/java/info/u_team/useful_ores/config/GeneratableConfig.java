package info.u_team.useful_ores.config;

import java.util.*;
import java.util.stream.Stream;

import org.apache.logging.log4j.*;

import com.google.gson.*;

import info.u_team.useful_ores.api.IGeneratable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.registries.ForgeRegistries;

public class GeneratableConfig implements IGeneratable {
	
	private final boolean enabled;
	
	private final boolean biomeCategoryBlackList;
	private final List<Category> biomeCategories;
	private final boolean biomeBlackList;
	private final List<Biome> biomes;
	
	private final int veinSize;
	private final Type type;
	private final CountRangeConfig countRangeConfig;
	private final DepthAverageConfig depthAverageConfig;
	
	public GeneratableConfig(boolean enabled, boolean biomeCategoryBlackList, Category[] biomeCategorys, boolean biomeBlackList, Biome[] biomes, int veinSize, Type type, CountRangeConfig countRangeConfig, DepthAverageConfig depthAverageConfig) {
		this(enabled, biomeCategoryBlackList, Arrays.asList(biomeCategorys), biomeBlackList, Arrays.asList(biomes), veinSize, type, countRangeConfig, depthAverageConfig);
	}
	
	public GeneratableConfig(boolean enabled, boolean biomeCategoryBlackList, List<Category> biomeCategorys, boolean biomeBlackList, List<Biome> biomes, int veinSize, Type type, CountRangeConfig countRangeConfig, DepthAverageConfig depthAverageConfig) {
		this.enabled = enabled;
		this.biomeCategoryBlackList = biomeCategoryBlackList;
		this.biomeCategories = biomeCategorys;
		this.biomeBlackList = biomeBlackList;
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
	public boolean isBiomeCategoryBlackList() {
		return biomeCategoryBlackList;
	}
	
	@Override
	public List<Category> getBiomeCategories() {
		return biomeCategories;
	}
	
	@Override
	public boolean isBiomeBlackList() {
		return biomeBlackList;
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
	public Type getType() {
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
		public JsonElement serialize(GeneratableConfig config, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
			
			final JsonObject object = new JsonObject();
			
			object.addProperty("enabled", config.isEnabled());
			
			{
				final JsonObject biomeCategoryObject = new JsonObject();
				biomeCategoryObject.addProperty("black_list", config.isBiomeCategoryBlackList());
				final JsonArray biomeCategoryList = new JsonArray();
				config.getBiomeCategories().stream().map(Biome.Category::getName).forEach(biomeCategoryList::add);
				biomeCategoryObject.add("list", biomeCategoryList);
				object.add("biome_category", biomeCategoryObject);
			}
			
			{
				final JsonObject biomeObject = new JsonObject();
				biomeObject.addProperty("black_list", config.isBiomeBlackList());
				final JsonArray biomeList = new JsonArray();
				config.getBiomes().stream().map(Biome::getRegistryName).map(ResourceLocation::toString).forEach(biomeList::add);
				biomeObject.add("list", biomeList);
				object.add("biome", biomeObject);
			}
			
			object.addProperty("vein_size", config.getVeinSize());
			
			{
				final JsonObject placementObject = new JsonObject();
				placementObject.addProperty("placement_type", config.getType().getName());
				if (config.getType() == Type.COUNT_RANGE) {
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
			}
			
			return object;
		}
		
		@Override
		public GeneratableConfig deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			final JsonObject object = json.getAsJsonObject();
			
			final boolean enabled = object.get("enabled").getAsBoolean();
			
			final JsonObject biomeCategoryObject = object.get("biome_category").getAsJsonObject();
			final boolean biomeCategoryBlackList = biomeCategoryObject.get("black_list").getAsBoolean();
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
			final boolean biomeBlackList = biomeObject.get("black_list").getAsBoolean();
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
			Type type = Type.byName(placementObject.get("placement_type").getAsString());
			if (type == null) {
				throw new JsonParseException("Placement type can't be null. Undefined type for string " + placementObject.get("placement_type").getAsString());
			}
			
			final CountRangeConfig countRangeConfig;
			final DepthAverageConfig depthAverageConfig;
			if (type == Type.COUNT_RANGE) {
				countRangeConfig = new CountRangeConfig(placementObject.get("count").getAsInt(), placementObject.get("bottom_offset").getAsInt(), placementObject.get("top_offset").getAsInt(), placementObject.get("maximum").getAsInt());
				depthAverageConfig = null;
			} else {
				depthAverageConfig = new DepthAverageConfig(placementObject.get("count").getAsInt(), placementObject.get("baseline").getAsInt(), placementObject.get("spread").getAsInt());
				countRangeConfig = null;
			}
			
			return new GeneratableConfig(enabled, biomeCategoryBlackList, biomeCategories, biomeBlackList, biomes, veinSize, type, countRangeConfig, depthAverageConfig);
		}
		
		private static final Map<String, Biome.Category> NAMES_TO_CATEGORY = new HashMap<>();
		static {
			Stream.of(Biome.Category.values()).forEach(category -> NAMES_TO_CATEGORY.put(category.getName(), category));
		}
		
	}
}
