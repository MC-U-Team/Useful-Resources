package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import org.apache.commons.lang3.ArrayUtils;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.ResourceDataType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.MathHelper;

public class VanillaDustResource extends BasicResource<VanillaDustResource> {
	
	public VanillaDustResource(String name, int color, int harvestLevel, float baseHardness, int minXp, int maxXp, BlockResourceType... existingOres) {
		super(name, color, ItemResourceType.DUST, Rarity.COMMON, ResourceDataType.DUST);
		
		setProperty("ingotModel", true);
		
		if (!ArrayUtils.contains(existingOres, ORE)) {
			addFeature(createOre(ORE, rarity, harvestLevel, baseHardness, baseHardness, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		if (!ArrayUtils.contains(existingOres, NETHER_ORE)) {
			addFeature(createOre(NETHER_ORE, rarity, harvestLevel, baseHardness, baseHardness, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		if (!ArrayUtils.contains(existingOres, END_ORE)) {
			addFeature(createOre(END_ORE, rarity, harvestLevel, baseHardness * 1.5F, baseHardness * 2, random -> MathHelper.nextInt(random, minXp, maxXp)));
		}
		
		addFeature(createMolten(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_END_ORE, rarity));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, rarity));
		
		addFeature(createBasicItem(PIECE, rarity));
	}
}
