package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import com.google.gson.*;

import net.minecraft.world.biome.Biome.Category;

public class BiomeCategorySerializer implements JsonSerializer<Category>, JsonDeserializer<Category> {
	
	private static final Map<String, Category> NAMES_TO_CATEGORY = new HashMap<>();
	static {
		Stream.of(Category.values()).forEach(category -> NAMES_TO_CATEGORY.put(category.getName(), category));
	}
	
	@Override
	public JsonElement serialize(Category category, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(category.getName());
	}
	
	@Override
	public Category deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Category category = NAMES_TO_CATEGORY.get(json.getAsString());
		if (category == null) {
			throw new JsonParseException("Biome category " + json.getAsString() + " could not be found.");
		}
		return category;
	}
}