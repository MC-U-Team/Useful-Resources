package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.*;

import info.u_team.u_team_core.item.armor.UArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class UArmorMaterialSerializer implements JsonSerializer<UArmorMaterial>, JsonDeserializer<UArmorMaterial> {
	
	private static final List<EquipmentSlotType> ARMOR_SLOTS = Arrays.asList(EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET);
	
	@Override
	public JsonElement serialize(UArmorMaterial material, Type typeOfSrc, JsonSerializationContext context) {
		final JsonObject object = new JsonObject();
		
		final JsonObject durabilityObject = new JsonObject();
		ARMOR_SLOTS.forEach(slot -> durabilityObject.addProperty(slot.getName(), material.getDurability(slot)));
		object.add("durability", durabilityObject);
		
		final JsonObject armorPointsObject = new JsonObject();
		ARMOR_SLOTS.forEach(slot -> armorPointsObject.addProperty(slot.getName(), material.getDamageReductionAmount(slot)));
		object.add("armor_points", armorPointsObject);
		
		object.addProperty("enchantability", material.getEnchantability());
		
		object.add("sound_event", context.serialize(material.getSoundEvent(), SoundEvent.class));
		object.addProperty("toughness", material.getToughness());
		return object;
	}
	
	@Override
	public UArmorMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		final JsonObject object = json.getAsJsonObject();
		
		final int[] durability = new int[ARMOR_SLOTS.size()];
		ARMOR_SLOTS.stream().collect(Collectors.toMap(slot -> slot, slot -> object.get("durability").getAsJsonObject().get(slot.getName()).getAsInt())).forEach((slot, value) -> durability[slot.getIndex()] = value);
		
		final int[] armorPoints = new int[ARMOR_SLOTS.size()];
		ARMOR_SLOTS.stream().collect(Collectors.toMap(slot -> slot, slot -> object.get("armor_points").getAsJsonObject().get(slot.getName()).getAsInt())).forEach((slot, value) -> armorPoints[slot.getIndex()] = value);
		
		final int enchantability = object.get("enchantability").getAsInt();
		final SoundEvent soundevent = context.deserialize(object.get("sound_event"), SoundEvent.class);
		
		final float toughness = object.get("toughness").getAsFloat();
		return new UArmorMaterial(durability, armorPoints, enchantability, soundevent, toughness, () -> Ingredient.EMPTY);
	}
}
