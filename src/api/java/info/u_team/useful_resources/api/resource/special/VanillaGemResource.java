package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.NETHER_ORE;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class VanillaGemResource extends BasicResource<VanillaGemResource> {
	
	public VanillaGemResource(String name, int color, int harvestLevel, float baseHardness) {
		super(name, color, ItemResourceType.GEM, Rarity.COMMON);
		addFeature(createOre(NETHER_ORE, Rarity.COMMON, harvestLevel, baseHardness * 0.75F, baseHardness * 1.25F));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
}
