package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import org.apache.commons.lang3.ArrayUtils;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;

public class VanillaIngotResource extends BasicResource<VanillaIngotResource> {
	
	private final int harvestLevel;
	private final float baseHardness;
	
	public VanillaIngotResource(String name, int color, int harvestLevel, float baseHardness, BlockResourceType... existingOres) {
		super(name, color, ItemResourceType.INGOT, Rarity.COMMON, ResourceType.INGOT);
		
		this.harvestLevel = harvestLevel;
		this.baseHardness = baseHardness;
		
		if (!ArrayUtils.contains(existingOres, ORE)) {
			addFeature(createOre(ORE, Rarity.COMMON, harvestLevel, baseHardness, baseHardness));
		}
		if (!ArrayUtils.contains(existingOres, NETHER_ORE)) {
			addFeature(createOre(NETHER_ORE, Rarity.COMMON, harvestLevel, baseHardness, baseHardness));
		}
		if (!ArrayUtils.contains(existingOres, END_ORE)) {
			addFeature(createOre(END_ORE, Rarity.COMMON, harvestLevel, baseHardness * 1.5F, baseHardness * 2));
		}
		
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createFence(Rarity.COMMON, harvestLevel, hardness - 1, resistance));
		
		addFeature(createMolten(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, Rarity.COMMON));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, Rarity.COMMON));
		addFeature(createBasicItem(CRUSHED_END_ORE, Rarity.COMMON));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, Rarity.COMMON));
		
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
	}
	
	public VanillaIngotResource setBars() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createBars(Rarity.COMMON, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setChain() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createChain(Rarity.COMMON, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setDoor() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createDoor(Rarity.COMMON, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setTrapDoor() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createTrapDoor(Rarity.COMMON, harvestLevel, hardness, resistance));
		return this;
	}
	
}
