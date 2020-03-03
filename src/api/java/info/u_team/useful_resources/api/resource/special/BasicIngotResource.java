package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.useful_resources.api.material.*;
import info.u_team.useful_resources.api.resource.Resource;
import info.u_team.useful_resources.api.resource.data.*;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;

public class BasicIngotResource extends Resource {
	
	private final Rarity rarity;
	
	private final IDataGeneratorConfigurator dataGeneratorConfigurator;
	
	public BasicIngotResource(String name, int color, Rarity rarity, int harvestLevel, float baseHardness) {
		super(name, color, ItemResourceType.INGOT);
		this.rarity = rarity;
		addFeature(createOre(ORE, rarity, harvestLevel, baseHardness, baseHardness * 1.5F));
		addFeature(createOre(NETHER_ORE, rarity, harvestLevel, baseHardness * 0.75F, baseHardness * 1.25F));
		addFeature(createBasicBlock(BLOCK, rarity, harvestLevel, baseHardness * 2, baseHardness * 4));
		
		addFeature(createBasicItem(INGOT, rarity));
		addFeature(createBasicItem(NUGGET, rarity));
		addFeature(createBasicItem(DUST, rarity));
		addFeature(createBasicItem(PLATE, rarity));
		addFeature(createBasicItem(DENSE_PLATE, rarity));
		addFeature(createBasicItem(GEAR, rarity));
		addFeature(createBasicItem(ROD, rarity));
		
		dataGeneratorConfigurator = new IDataGeneratorConfigurator() {
			
			@Override
			public OreType getOreType() {
				return OreType.INGOT;
			}
		};
	}
	
	public BasicIngotResource setTools(IToolMaterial toolMaterial) {
		addFeature(createTools(rarity, new WrappedToolMaterial(toolMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType())))));
		return this;
	}
	
	public BasicIngotResource setArmor(IArmorMaterial armorMaterial) {
		addFeature(createArmor(rarity, new WrappedArmorMaterial(armorMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType())))));
		return this;
	}
	
	public BasicIngotResource setHorseArmor(int armorPoints) {
		addFeature(createHorseArmor(rarity, armorPoints));
		return this;
	}
	
	@Override
	public IDataGeneratorConfigurator getDataGeneratorConfigurator() {
		return dataGeneratorConfigurator;
	}
	
}
