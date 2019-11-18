package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.function.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.item.tool.*;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.block.*;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import info.u_team.useful_resources.item.*;
import net.minecraft.block.Block;
import net.minecraft.item.*;

public class CommonResourceBuilder {
	
	public static IResourceFeatureBuilder createBasicBlock(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return basicBuilder((name, feature) -> {
			feature.add(type, new BasicBlock(basicName(name, type), rarity, harvestLevel, hardness, resistance));
		});
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return createOre(type, rarity, harvestLevel, hardness, resistance, random -> 0);
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		return basicBuilder((name, feature) -> {
			feature.add(type, new OreBlock(basicName(name, type), rarity, harvestLevel, hardness, resistance, experienceDrop));
		});
	}
	
	public static IResourceFeatureBuilder createBasicItem(ItemResourceType type, Rarity rarity) {
		return basicBuilder((name, feature) -> {
			feature.add(type, new BasicItem(basicName(name, type), rarity));
		});
	}
	
	public static IResourceFeatureBuilder createTools(Rarity rarity, IToolMaterial material) {
		return basicBuilder((name, feature) -> {
			final ToolSet toolSet = ToolSetCreator.create(name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.AXE, toolSet.getAxe());
			feature.add(ItemResourceType.HOE, toolSet.getHoe());
			feature.add(ItemResourceType.PICKAXE, toolSet.getPickaxe());
			feature.add(ItemResourceType.SHOVEL, toolSet.getShovel());
			feature.add(ItemResourceType.SWORD, toolSet.getSword());
		});
	}
	
	public static IResourceFeatureBuilder createArmor(Rarity rarity, IArmorMaterial material) {
		return basicBuilder((name, feature) -> {
			final ArmorSet armorSet = ArmorSetCreator.create(name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.HELMET, armorSet.getHelmet());
			feature.add(ItemResourceType.CHESTPLATE, armorSet.getChestplate());
			feature.add(ItemResourceType.LEGGINGS, armorSet.getLeggings());
			feature.add(ItemResourceType.BOOTS, armorSet.getBoots());
		});
	}
	
	public static IResourceFeatureBuilder createHorseArmor(Rarity rarity, int armorPoints) {
		return basicBuilder((name, feature) -> {
			feature.add(ItemResourceType.HORSE_ARMOR, new BasicHorseArmorItem(name, rarity, armorPoints));
		});
	}
	
	private static String basicName(String name, IResourceType type) {
		return name + "_" + type.getName();
	}
	
	private static IResourceFeatureBuilder basicBuilder(BiConsumer<String, ResourceFeature> consumer) {
		return name -> {
			final ResourceFeature feature = new ResourceFeature();
			consumer.accept(name, feature);
			return feature;
		};
		
	}
	
	private static class ResourceFeature implements IResourceFeature {
		
		private final Map<BlockResourceType, Block> blocks;
		private final Map<ItemResourceType, Item> items;
		
		private ResourceFeature() {
			blocks = new EnumMap<>(BlockResourceType.class);
			items = new EnumMap<>(ItemResourceType.class);
		}
		
		private Block add(BlockResourceType type, Block block) {
			blocks.put(type, block);
			return block;
		}
		
		private Item add(ItemResourceType type, Item item) {
			items.put(type, item);
			return item;
		}
		
		@Override
		public Map<BlockResourceType, Block> getBlocks() {
			return blocks;
		}
		
		@Override
		public Map<ItemResourceType, Item> getItems() {
			return items;
		}
		
	}
	
}
