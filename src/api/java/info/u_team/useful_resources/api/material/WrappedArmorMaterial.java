package info.u_team.useful_resources.api.material;

import java.util.function.Supplier;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;

public class WrappedArmorMaterial implements IArmorMaterial {
	
	private final IArmorMaterial armorMaterial;
	private final Supplier<Ingredient> repairIngredient;
	
	public WrappedArmorMaterial(IArmorMaterial armorMaterial, Supplier<Ingredient> repairIngredient) {
		this.armorMaterial = armorMaterial;
		this.repairIngredient = repairIngredient;
	}
	
	@Override
	public int getDamageReductionAmount(EquipmentSlotType type) {
		return armorMaterial.getDamageReductionAmount(type);
	}
	
	@Override
	public int getDurability(EquipmentSlotType type) {
		return armorMaterial.getDurability(type);
	}
	
	@Override
	public int getEnchantability() {
		return armorMaterial.getEnchantability();
	}
	
	@Override
	public String getName() {
		return armorMaterial.getName();
	}
	
	@Override
	public SoundEvent getSoundEvent() {
		return armorMaterial.getSoundEvent();
	}
	
	@Override
	public float getToughness() {
		return armorMaterial.getToughness();
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return repairIngredient.get();
	}

	@Override
	public float getKnockbackResistance() {
		return armorMaterial.getKnockbackResistance();
	}
}
