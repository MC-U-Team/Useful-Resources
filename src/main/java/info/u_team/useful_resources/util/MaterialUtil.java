package info.u_team.useful_resources.util;

import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import info.u_team.useful_resources.item.HorseArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;

public class MaterialUtil {
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability) {
		return createArmor(durability, armorPoints, enchantability, 0);
	}
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability, float toughness) {
		return createArmor(durability, armorPoints, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughness);
	}
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability, SoundEvent soundevent, float toughness) {
		return new UArmorMaterial(durability, armorPoints, enchantability, soundevent, toughness, () -> Ingredient.EMPTY);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability) {
		return createArmor(durabilityFactor, armorPoints, enchantability, 0);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability, float toughness) {
		return createArmor(durabilityFactor, armorPoints, enchantability, SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughness);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability, SoundEvent soundevent, float toughness) {
		return new UArmorMaterialVanilla(durabilityFactor, armorPoints, enchantability, soundevent, toughness, () -> Ingredient.EMPTY);
	}
	
	public static HorseArmorMaterial createHorseArmor(int armorPoints) {
		return new HorseArmorMaterial(armorPoints);
	}
	
	public static UToolMaterial createTools(float[] additionalDamage, float[] speed, int harvestlevel, int durability, float efficiency, float baseDamage, int enchantability) {
		return new UToolMaterial(additionalDamage, speed, harvestlevel, durability, efficiency, baseDamage, enchantability, () -> Ingredient.EMPTY);
	}
}
