package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.NETHER_ORE;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class VanillaDustResource extends BasicResource<VanillaDustResource> {
	
	public VanillaDustResource(String name, int color, int harvestLevel, float hardness, float resistance) {
		super(name, color, ItemResourceType.DUST, Rarity.COMMON, ResourceType.DUST);
		
		setProperty("ingotModel", true);
		
		addFeature(createOre(NETHER_ORE, Rarity.COMMON, harvestLevel, hardness, resistance));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, Rarity.COMMON));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, Rarity.COMMON));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, Rarity.COMMON));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
	}
}
