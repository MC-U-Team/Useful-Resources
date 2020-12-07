package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class BasicRadioActiveIngotResource extends BasicResource<BasicRadioActiveIngotResource> {
	
	public BasicRadioActiveIngotResource(String name, int color, Rarity rarity, int harvestLevel, float baseHardness) {
		super(name, color, ItemResourceType.INGOT, rarity, ResourceType.INGOT);
		addFeature(createOre(ORE, rarity, harvestLevel, baseHardness, baseHardness * 1.5F));
		addFeature(createOre(NETHER_ORE, rarity, harvestLevel, baseHardness * 0.75F, baseHardness * 1.25F));
		addFeature(createOre(END_ORE, rarity, harvestLevel, baseHardness * 1.5F, baseHardness * 2));
		addFeature(createBasicBlock(BLOCK, rarity, harvestLevel, baseHardness * 2, baseHardness * 4));
		
		addFeature(createMolten(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_END_ORE, rarity));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, rarity));
		
		addFeature(createBasicItem(INGOT, rarity));
		addFeature(createBasicItem(NUGGET, rarity));
		addFeature(createBasicItem(DUST, rarity));
		addFeature(createBasicItem(PLATE, rarity));
		addFeature(createBasicItem(DENSE_PLATE, rarity));
		addFeature(createBasicItem(GEAR, rarity));
		addFeature(createBasicItem(ROD, rarity));
	}
}
