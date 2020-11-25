package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.ItemResourceType;
import info.u_team.useful_resources.util.LootTableUtil;
import net.minecraft.item.Rarity;
import net.minecraft.util.math.MathHelper;

public class BasicGemResource extends BasicResource<BasicGemResource> {
	
	public BasicGemResource(String name, int color, Rarity rarity, int harvestLevel, float baseHardness, int minXp, int maxXp) {
		super(name, color, ItemResourceType.GEM, rarity, ResourceType.GEM);
		addFeature(createOre(ORE, rarity, harvestLevel, baseHardness, baseHardness * 1.5F, random -> MathHelper.nextInt(random, minXp, maxXp)));
		addFeature(createOre(NETHER_ORE, rarity, harvestLevel, baseHardness * 0.75F, baseHardness * 1.25F, random -> MathHelper.nextInt(random, minXp, maxXp)));
		addFeature(createOre(END_ORE, rarity, harvestLevel, baseHardness * 1.5F, baseHardness * 2, random -> MathHelper.nextInt(random, minXp, maxXp)));
		addFeature(createBasicBlock(BLOCK, rarity, harvestLevel, baseHardness * 2, baseHardness * 4));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_END_ORE, rarity));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, rarity));
		
		addFeature(createBasicItem(GEM, rarity));
		addFeature(createBasicItem(PIECE, rarity));
		addFeature(createBasicItem(DUST, rarity));
		addFeature(createBasicItem(PLATE, rarity));
		addFeature(createBasicItem(DENSE_PLATE, rarity));
		addFeature(createBasicItem(GEAR, rarity));
		addFeature(createBasicItem(ROD, rarity));
		
		setLootTableWithFortune(ORE, GEM, LootTableUtil::createFortuneBlockLootTable);
		setLootTableWithFortune(NETHER_ORE, GEM, LootTableUtil::createFortuneBlockLootTable);
	}
}
