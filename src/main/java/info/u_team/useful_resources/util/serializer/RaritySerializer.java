package info.u_team.useful_resources.util.serializer;

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
		Rarity rarity = Enum.valueOf(Rarity.class, json.getAsString().toUpperCase());
		if (rarity == null) {
			throw new JsonParseException("Rarity " + json.getAsString() + " could not be found.");
		}
		return rarity;
	}
	
}
