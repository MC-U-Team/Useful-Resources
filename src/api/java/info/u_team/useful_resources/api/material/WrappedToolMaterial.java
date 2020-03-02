package info.u_team.useful_resources.api.material;

import java.util.function.Supplier;

import info.u_team.u_team_core.api.IToolMaterial;
import net.minecraft.item.crafting.Ingredient;

public class WrappedToolMaterial implements IToolMaterial {
	
	private final IToolMaterial toolMaterial;
	private final Supplier<Ingredient> repairIngredient;
	
	public WrappedToolMaterial(IToolMaterial toolMaterial, Supplier<Ingredient> repairIngredient) {
		this.toolMaterial = toolMaterial;
		this.repairIngredient = repairIngredient;
	}
	
	@Override
	public float getAdditionalDamage(Tools tools) {
		return toolMaterial.getAdditionalDamage(tools);
	}
	
	@Override
	public float getAttackDamage() {
		return toolMaterial.getAttackDamage();
	}
	
	@Override
	public float getAttackSpeed(Tools tools) {
		return toolMaterial.getAttackSpeed(tools);
	}
	
	@Override
	public float getEfficiency() {
		return toolMaterial.getEfficiency();
	}
	
	@Override
	public int getEnchantability() {
		return toolMaterial.getEnchantability();
	}
	
	@Override
	public int getHarvestLevel() {
		return toolMaterial.getHarvestLevel();
	}
	
	@Override
	public int getMaxUses() {
		return toolMaterial.getMaxUses();
	}
	
	@Override
	public Ingredient getRepairMaterial() {
		return repairIngredient.get();
	}
}
