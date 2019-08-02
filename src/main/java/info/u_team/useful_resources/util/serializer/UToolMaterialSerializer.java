package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.*;

import info.u_team.u_team_core.api.IToolMaterial.Tools;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import net.minecraft.item.crafting.Ingredient;

public class UToolMaterialSerializer implements JsonSerializer<UToolMaterial>, JsonDeserializer<UToolMaterial> {
	
	private static final List<Tools> TOOLS = Arrays.asList(Tools.AXE, Tools.HOE, Tools.PICKAXE, Tools.SHOVEL, Tools.SWORD);
	
	@Override
	public JsonElement serialize(UToolMaterial material, Type typeOfSrc, JsonSerializationContext context) {
		final JsonObject object = new JsonObject();
		
		final JsonObject additionalDamageObject = new JsonObject();
		TOOLS.forEach(tool -> additionalDamageObject.addProperty(tool.name().toLowerCase(), material.getAdditionalDamage(tool)));
		object.add("additional_damage", additionalDamageObject);
		
		final JsonObject attackSpeedObject = new JsonObject();
		TOOLS.forEach(tool -> attackSpeedObject.addProperty(tool.name().toLowerCase(), material.getAttackSpeed(tool)));
		object.add("attack_speed", attackSpeedObject);
		
		object.addProperty("harvest_level", material.getHarvestLevel());
		object.addProperty("durability", material.getMaxUses());
		object.addProperty("efficiency", material.getEfficiency());
		object.addProperty("base_damage", material.getAttackDamage());
		object.addProperty("enchantability", material.getEnchantability());
		return object;
	}
	
	@Override
	public UToolMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject object = json.getAsJsonObject();
		
		final float[] additionalDamage = new float[TOOLS.size()];
		TOOLS.stream().collect(Collectors.toMap(tool -> tool, tool -> object.get("additional_damage").getAsJsonObject().get(tool.name().toLowerCase()).getAsFloat())).forEach((tool, value) -> additionalDamage[tool.getIndex()] = value);
		
		final float[] attackSpeed = new float[TOOLS.size()];
		TOOLS.stream().collect(Collectors.toMap(tool -> tool, tool -> object.get("attack_speed").getAsJsonObject().get(tool.name().toLowerCase()).getAsFloat())).forEach((tool, value) -> attackSpeed[tool.getIndex()] = value);
		
		final int harvestLevel = object.get("harvest_level").getAsInt();
		final int durability = object.get("durability").getAsInt();
		final float efficiency = object.get("efficiency").getAsFloat();
		final float baseDamage = object.get("base_damage").getAsFloat();
		final int enchantability = object.get("enchantability").getAsInt();
		return new UToolMaterial(additionalDamage, attackSpeed, harvestLevel, durability, efficiency, baseDamage, enchantability, () -> Ingredient.EMPTY);
	}
	
}
