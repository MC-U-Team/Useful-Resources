package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;

import org.apache.commons.lang3.ArrayUtils;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.MathHelper;

public class VanillaGemResource extends BasicResource<VanillaGemResource> {
	
	public VanillaGemResource(String name, int color, int harvestLevel, float baseHardness, boolean ingotModel, int minXp, int maxXp, BlockResourceType... existingOres) {
		super(name, color, ItemResourceType.GEM, Rarity.COMMON, ResourceType.GEM);
		
		setProperty("ingotModel", ingotModel);
		
		if (!ArrayUtils.contains(existingOres, ORE)) {
			addFeature(createOre(ORE, Rarity.COMMON, harvestLevel, baseHardness, baseHardness, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		if (!ArrayUtils.contains(existingOres, NETHER_ORE)) {
			addFeature(createOre(NETHER_ORE, Rarity.COMMON, harvestLevel, baseHardness, baseHardness, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		if (!ArrayUtils.contains(existingOres, END_ORE)) {
			addFeature(createOre(END_ORE, Rarity.COMMON, harvestLevel, baseHardness * 1.5F, baseHardness * 2, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, Rarity.COMMON));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, Rarity.COMMON));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, Rarity.COMMON));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
}
