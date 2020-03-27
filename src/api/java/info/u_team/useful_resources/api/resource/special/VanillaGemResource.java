package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.MathHelper;

public class VanillaGemResource extends BasicResource<VanillaGemResource> {
	
	public VanillaGemResource(String name, int color, int harvestLevel, float baseHardness, boolean hasOverworldOre) {
		super(name, color, ItemResourceType.GEM, Rarity.COMMON);
		setProperty("oreLootTable", "fortune");
		setProperty("oreLootTableDrop", ItemResourceType.GEM);
		
		addFeature(createOre(hasOverworldOre ? NETHER_ORE : ORE, Rarity.COMMON, harvestLevel, baseHardness * 0.75F, baseHardness * 1.25F, random -> MathHelper.nextInt(random, 2, 6)));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
}
