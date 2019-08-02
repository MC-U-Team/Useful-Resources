package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

import com.google.gson.*;

import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;

public class FillerBlockTypeSerializer implements JsonSerializer<FillerBlockType>, JsonDeserializer<FillerBlockType> {
	
	private static final Map<String, FillerBlockType> NAMES_TO_TYPE = new HashMap<>();
	static {
		Stream.of(FillerBlockType.values()).forEach(type -> NAMES_TO_TYPE.put(type.func_214737_a(), type));
	}
	
	@Override
	public JsonElement serialize(FillerBlockType type, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(type.func_214737_a());
	}
	
	@Override
	public FillerBlockType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		FillerBlockType type = NAMES_TO_TYPE.get(json.getAsString());
		if (type == null) {
			throw new JsonParseException("Filler block type " + json.getAsString() + " could not be found.");
		}
		return type;
	}
	
}