package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;

public class VanillaIngotResource extends BasicResource<VanillaIngotResource> {
	
	public VanillaIngotResource(String name, int color, int harvestLevel, float hardness, float resistance) {
		this(name, color, harvestLevel, hardness, resistance, null);
	}
	
	public VanillaIngotResource(String name, int color, int harvestLevel, float hardness, float resistance, BlockResourceType oreType) {
		super(name, color, ItemResourceType.INGOT, Rarity.COMMON, ResourceType.INGOT);
		
		if (oreType != null) {
			addFeature(createOre(oreType, Rarity.COMMON, harvestLevel, hardness, resistance));
		}
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, Rarity.COMMON));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, Rarity.COMMON));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, Rarity.COMMON));
		
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
}
