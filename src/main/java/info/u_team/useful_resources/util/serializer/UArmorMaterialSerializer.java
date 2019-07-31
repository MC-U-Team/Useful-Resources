package info.u_team.useful_resources.util.serializer;

import java.lang.reflect.Type;
import java.util.stream.Stream;

import com.google.gson.*;

import info.u_team.u_team_core.item.armor.UArmorMaterial;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;

public class UArmorMaterialSerializer implements JsonSerializer<UArmorMaterial>, JsonDeserializer<UArmorMaterial> {
	
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] { EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET };
	
	@Override
	public JsonElement serialize(UArmorMaterial material, Type typeOfSrc, JsonSerializationContext context) {
		System.out.println("--------------------------------------------------------------------------------------------");
		final JsonObject object = new JsonObject();
		
		final JsonObject durabilityObject = new JsonObject();
		Stream.of(ARMOR_SLOTS).forEach(slot -> durabilityObject.addProperty(slot.getName(), material.getDurability(slot)));
		object.add("durability", durabilityObject);
		
		final JsonObject armorPointsObject = new JsonObject();
		Stream.of(ARMOR_SLOTS).forEach(slot -> armorPointsObject.addProperty(slot.getName(), material.getDamageReductionAmount(slot)));
		object.add("armor_points", armorPointsObject);
		
		object.addProperty("enchantability", material.getEnchantability());
		
		object.add("sound_event", context.serialize(material.getSoundEvent(), SoundEvent.class));
		object.addProperty("toughness", material.getToughness());
		
		object.add("repair_material", material.getRepairMaterial().serialize());
		
		return object;
	}
	
	@Override
	public UArmorMaterial deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new UArmorMaterial(new int[] { 0, 0, 0, 0 }, new int[] { 0, 0, 0, 0 }, 2, SoundEvents.AMBIENT_CAVE, 1, () -> Ingredient.fromItems(Items.ACACIA_BOAT));
	}
}
