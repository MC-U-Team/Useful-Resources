package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;

import com.google.gson.*;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.*;

public class BiomeSerializer implements JsonSerializer<Biome>, JsonDeserializer<Biome> {
	
	@Override
	public JsonElement serialize(Biome biome, Type typeOfSrc, JsonSerializationContext context) {
		return new JsonPrimitive(biome.getRegistryName().toString());
	}
	
	@Override
	public Biome deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		Biome biome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(json.getAsString()));
		if (biome == null) {
			throw new JsonParseException("Biome " + json.getAsString() + " could not be found.");
		}
		return biome;
	}
}
