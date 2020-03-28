package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.MathHelper;

public class VanillaGemResource extends BasicResource<VanillaGemResource> {
	
	public VanillaGemResource(String name, int color, int harvestLevel, float hardness, float resistance, BlockResourceType oreType, int minXp, int maxXp) {
		super(name, color, ItemResourceType.GEM, Rarity.COMMON, ResourceType.GEM);
		
		addFeature(createOre(oreType, Rarity.COMMON, harvestLevel, hardness, resistance, random -> MathHelper.nextInt(random, minXp, maxXp)));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
}
