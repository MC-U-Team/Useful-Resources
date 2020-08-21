package info.u_team.useful_resources.util;

import java.util.function.Supplier;

import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;

public class MaterialUtil {
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability) {
		return createArmor(durability, armorPoints, enchantability, 0, 0);
	}
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability, float knockbackResistance) {
		return createArmor(durability, armorPoints, enchantability, 0, knockbackResistance);
	}
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability, float toughness, float knockbackResistance) {
		return createArmor(durability, armorPoints, enchantability, () -> SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughness, knockbackResistance);
	}
	
	public static UArmorMaterial createArmor(int[] durability, int[] armorPoints, int enchantability, Supplier<SoundEvent> soundevent, float toughness, float knockbackResistance) {
		return new UArmorMaterial(durability, armorPoints, enchantability, soundevent, toughness, knockbackResistance, () -> Ingredient.EMPTY);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability) {
		return createArmor(durabilityFactor, armorPoints, enchantability, 0, 0);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability, float knockbackResistance) {
		return createArmor(durabilityFactor, armorPoints, enchantability, 0, knockbackResistance);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability, float toughness, float knockbackResistance) {
		return createArmor(durabilityFactor, armorPoints, enchantability, () -> SoundEvents.ITEM_ARMOR_EQUIP_IRON, toughness, knockbackResistance);
	}
	
	public static UArmorMaterial createArmor(int durabilityFactor, int[] armorPoints, int enchantability, Supplier<SoundEvent> soundevent, float toughness, float knockbackResistance) {
		return new UArmorMaterialVanilla(durabilityFactor, armorPoints, enchantability, soundevent, toughness, knockbackResistance, () -> Ingredient.EMPTY);
	}
	
	public static UToolMaterial createTools(float[] additionalDamage, float[] speed, int harvestlevel, int durability, float efficiency, float baseDamage, int enchantability) {
		return new UToolMaterial(additionalDamage, speed, harvestlevel, durability, efficiency, baseDamage, enchantability, () -> Ingredient.EMPTY);
	}
	
}
