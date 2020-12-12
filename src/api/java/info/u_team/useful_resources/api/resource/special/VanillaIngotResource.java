package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import org.apache.commons.lang3.ArrayUtils;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.ResourceDataType;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.item.Rarity;

public class VanillaIngotResource extends BasicResource<VanillaIngotResource> {
	
	private final int harvestLevel;
	private final float baseHardness;
	
	public VanillaIngotResource(String name, int color, int harvestLevel, float baseHardness, BlockResourceType... existingOres) {
		super(name, color, ItemResourceType.INGOT, Rarity.COMMON, ResourceDataType.INGOT);
		
		this.harvestLevel = harvestLevel;
		this.baseHardness = baseHardness;
		
		if (!ArrayUtils.contains(existingOres, ORE)) {
			addFeature(createOre(ORE, rarity, harvestLevel, baseHardness, baseHardness));
		}
		if (!ArrayUtils.contains(existingOres, NETHER_ORE)) {
			addFeature(createOre(NETHER_ORE, rarity, harvestLevel, baseHardness, baseHardness));
		}
		if (!ArrayUtils.contains(existingOres, END_ORE)) {
			addFeature(createOre(END_ORE, rarity, harvestLevel, baseHardness * 1.5F, baseHardness * 2));
		}
		
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createFence(rarity, harvestLevel, hardness - 1, resistance));
		
		addFeature(createMolten(0xFF000000 + color));
		
		addFeature(createBasicItem(CRUSHED_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_NETHER_ORE, rarity));
		addFeature(createBasicItem(CRUSHED_END_ORE, rarity));
		addFeature(createBasicItem(PURE_CRUSHED_ORE, rarity));
		
		addFeature(createBasicItem(DUST, rarity));
		addFeature(createBasicItem(PLATE, rarity));
		addFeature(createBasicItem(DENSE_PLATE, rarity));
		addFeature(createBasicItem(GEAR, rarity));
		addFeature(createBasicItem(ROD, rarity));
	}
	
	public VanillaIngotResource setBars() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createBars(rarity, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setChain() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createChain(rarity, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setDoor() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createDoor(rarity, harvestLevel, hardness, resistance));
		return this;
	}
	
	public VanillaIngotResource setTrapDoor() {
		final float hardness = baseHardness < 5 ? 5 : baseHardness;
		final float resistance = hardness + 1;
		addFeature(createTrapDoor(rarity, harvestLevel, hardness, resistance));
		return this;
	}
	
}
