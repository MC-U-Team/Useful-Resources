package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;

import com.google.gson.*;

import net.minecraft.util.*;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventSerializer implements JsonSerializer<SoundEvent>, JsonDeserializer<SoundEvent> {
	
	@Override
	public JsonElement serialize(SoundEvent soundEvent, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(soundEvent.getRegistryName().toString());
	}
	
	@Override
	public SoundEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(json.getAsString()));
		if (soundEvent == null) {
			throw new JsonParseException("Sound event " + json.getAsString() + " could not be found.");
		}
		return soundEvent;
	}
}
