package info.u_team.useful_resources.config;

import java.lang.reflect.Type;

import com.google.gson.*;

import info.u_team.useful_resources.api.resource.config.generation.*;
import net.minecraft.world.gen.placement.*;

public class ResourceGenerationType implements IResourceGenerationTypeConfig {
	
	private final GenerationType type;
	private final CountRangeConfig countRange;
	private final DepthAverageConfig depthAverage;
	
	public ResourceGenerationType(DepthAverageConfig depthAverage) {
		this(GenerationType.COUNT_DEPTH_AVERAGE, null, depthAverage);
	}
	
	public ResourceGenerationType(CountRangeConfig countRage) {
		this(GenerationType.COUNT_RANGE, countRage, null);
	}
	
	public ResourceGenerationType(GenerationType type, CountRangeConfig countRange, DepthAverageConfig depthAverage) {
		this.type = type;
		this.countRange = countRange;
		this.depthAverage = depthAverage;
	}
	
	@Override
	public GenerationType getGenerationType() {
		return type;
	}
	
	@Override
	public CountRangeConfig getCountRangeConfig() {
		return countRange;
	}
	
	@Override
	public DepthAverageConfig getDepthAverageConfig() {
		return depthAverage;
	}
	
	public static class Serializer implements JsonSerializer<ResourceGenerationType>, JsonDeserializer<ResourceGenerationType> {
		
		@Override
		public JsonElement serialize(ResourceGenerationType generationType, Type typeOfSrc, JsonSerializationContext context) {
			final GenerationType type = generationType.getGenerationType();
			final CountRangeConfig countRange = generationType.getCountRangeConfig();
			final DepthAverageConfig depthAverage = generationType.getDepthAverageConfig();
			
			final JsonObject object = new JsonObject();
			
			object.addProperty("type", type.getName());
			if (type == GenerationType.COUNT_RANGE) {
				object.addProperty("count", countRange.count);
				object.addProperty("bottom_offset", countRange.bottomOffset);
				object.addProperty("top_offset", countRange.topOffset);
				object.addProperty("maximum", countRange.maximum);
			} else {
				object.addProperty("count", depthAverage.count);
				object.addProperty("baseline", depthAverage.baseline);
				object.addProperty("spread", depthAverage.spread);
			}
			return object;
		}
		
		@Override
		public ResourceGenerationType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			final JsonObject object = json.getAsJsonObject();
			final GenerationType type = GenerationType.byName(object.get("type").getAsString());
			if (type == null) {
				throw new JsonParseException("Generation type " + json.getAsString() + " could not be found.");
			}
			final CountRangeConfig countRange;
			final DepthAverageConfig depthAverage;
			if (type == GenerationType.COUNT_RANGE) {
				countRange = new CountRangeConfig(object.get("count").getAsInt(), object.get("bottom_offset").getAsInt(), object.get("top_offset").getAsInt(), object.get("maximum").getAsInt());
				depthAverage = null;
			} else {
				countRange = null;
				depthAverage = new DepthAverageConfig(object.get("count").getAsInt(), object.get("baseline").getAsInt(), object.get("spread").getAsInt());
			}
			return new ResourceGenerationType(type, countRange, depthAverage);
		}
	}
}
