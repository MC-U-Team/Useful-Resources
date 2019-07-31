package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.stream.Stream;

import com.google.gson.*;

import info.u_team.u_team_core.api.IToolMaterial.Tools;
import info.u_team.u_team_core.item.armor.UArmorMaterial;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import info.u_team.useful_resources.resource.TestToolMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

public class UToolMaterialSerializer implements JsonSerializer<UToolMaterial>, JsonDeserializer<UToolMaterial> {
	
	@Override
	public JsonElement serialize(UToolMaterial material, Type typeOfSrc, JsonSerializationContext context) {
		System.out.println("XDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
		final JsonObject object = new JsonObject();
		
		final JsonArray durabilityArray = new JsonArray();
		durabilityArray.add(true);
		Stream.of(Tools.values()).forEach(tool -> {
			JsonObject durabilityObject = new JsonObject();
			durabilityObject.addProperty(tool.name().toLowerCase(), material.getAdditionalDamage(tool));
			durabilityArray.add(durabilityObject);
		});
		object.add("durability", durabilityArray);
		return object;
	}
	
	@Override
	public UToolMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return null;
	}
	
}
