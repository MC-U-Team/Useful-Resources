package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;

import com.google.gson.*;

import info.u_team.useful_resources.api.resource.config.IResourceGenerationConfig.ListType;

public class ListTypeSerializer implements JsonSerializer<ListType>, JsonDeserializer<ListType> {
	
	@Override
	public JsonElement serialize(ListType type, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(type.name().toLowerCase());
	}
	
	@Override
	public ListType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		ListType type = Enum.valueOf(ListType.class, json.getAsString().toUpperCase());
		if (type == null) {
			throw new JsonParseException("List type " + json.getAsString() + " could not be found.");
		}
		return type;
	}
	
}
