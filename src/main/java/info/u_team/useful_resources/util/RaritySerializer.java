package info.u_team.useful_resources.util;

import java.lang.reflect.Type;

import com.google.gson.*;

import net.minecraft.item.Rarity;

public class RaritySerializer implements JsonSerializer<Rarity>, JsonDeserializer<Rarity> {
	
	@Override
	public JsonElement serialize(Rarity rarity, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(rarity.name().toLowerCase());
	}
	
	@Override
	public Rarity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return Enum.valueOf(Rarity.class, json.getAsString().toUpperCase());
	}
	
}
