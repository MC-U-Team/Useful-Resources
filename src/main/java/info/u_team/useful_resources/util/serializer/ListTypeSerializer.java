package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;

import com.google.gson.*;

import info.u_team.useful_resources.api.ListType;

public class ListTypeSerializer implements JsonSerializer<ListType>, JsonDeserializer<ListType> {
	
	@Override
	public JsonElement serialize(ListType type, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(type.getName());
	}
	
	@Override
	public ListType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		ListType type = ListType.byName(json.getAsString());
		if (type == null) {
			throw new JsonParseException("List type " + json.getAsString() + " could not be found.");
		}
		return type;
	}
	
}
