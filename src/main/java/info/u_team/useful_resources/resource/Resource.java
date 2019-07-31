package info.u_team.useful_resources.resource;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;

import com.google.common.collect.Maps;
import com.google.gson.*;

import info.u_team.u_team_core.api.IToolMaterial.Tools;
import info.u_team.u_team_core.item.armor.UArmorMaterial;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.*;
import info.u_team.useful_resources.api.resource.type.*;
import info.u_team.useful_resources.config.*;
import info.u_team.useful_resources.type.*;
import info.u_team.useful_resources.util.ConfigUtil;
import info.u_team.useful_resources.util.serializer.*;
import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraftforge.fml.loading.FMLPaths;

public class Resource implements IResource {
	
	private final String name;
	private final IResourceBlocks blocks;
	private final IResourceItems items;
	
	public Resource(String name, Map<ResourceBlockTypes, Block> blocks, Map<ResourceItemTypes, Item> items, Map<ResourceBlockTypes, ResourceGenerationConfig> generationConfig, UArmorMaterial armorMaterial, UToolMaterial toolMaterial) {
		this.name = name;
		this.blocks = new ResourceBlocks(this, blocks, generationConfig);
		this.items = new ResourceItems(this, items, armorMaterial, toolMaterial);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public IResourceBlocks getBlocks() {
		return blocks;
	}
	
	@Override
	public IResourceItems getItems() {
		return items;
	}
	
	public static class Builder {
		
		private static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve(UsefulResourcesMod.MODID);
		private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Rarity.class, new RaritySerializer()).registerTypeAdapter(ListType.class, new ListTypeSerializer()).registerTypeHierarchyAdapter(ResourceGenerationType.class, new ResourceGenerationType.Serializer()).registerTypeHierarchyAdapter(Biome.class, new BiomeSerializer()).registerTypeAdapter(Category.class, new BiomeCategorySerializer()).registerTypeAdapter(FillerBlockType.class, new FillerBlockTypeSerializer()).registerTypeHierarchyAdapter(SoundEvent.class, new SoundEventSerializer()).registerTypeHierarchyAdapter(UArmorMaterial.class, new UArmorMaterialSerializer()).registerTypeHierarchyAdapter(UToolMaterial.class, new UToolMaterialSerializer()).create();
		
		private static final TriFunction<Exception, String, String, RuntimeException> CONFIG_EXCEPTION_WITH_TYPE = (ex, name, typeName) -> new RuntimeException("Could not access config file for resource " + name + " with type " + typeName, ex);
		private static final BiFunction<Exception, String, RuntimeException> CONFIG_EXCEPTION = (ex, name) -> new RuntimeException("Could not access config file for resource " + name, ex);
		
		private final String name;
		
		private final float commonHardness;
		private final int commonHarvestLevel;
		
		private Rarity commonRarity;
		private boolean ores;
		
		private final Map<ResourceBlockTypes, ResourceBlockConfig> defaultBlockSettings;
		private final Map<ResourceItemTypes, ResourceItemConfig> defaultItemSettings;
		
		private final Map<ResourceBlockTypes, ResourceGenerationConfig> defaultGenerationSettings;
		
		private boolean armor;
		private UArmorMaterial defaultArmorMaterial;
		
		private boolean tools;
		private UToolMaterial defaultToolMaterial;
		
		private final Map<ResourceBlockTypes, TriFunction<IResource, IResourceBlockType, IResourceBlockConfig, Block>> specialBlocks;
		private final Map<ResourceItemTypes, TriFunction<IResource, IResourceItemType, IResourceItemConfig, Item>> specialItems;
		
		public Builder(String name, float commonHardness, int commonHarvestLevel) {
			this.name = name;
			this.commonHardness = commonHardness;
			this.commonHarvestLevel = commonHarvestLevel;
			commonRarity = Rarity.COMMON;
			ores = true;
			defaultBlockSettings = Maps.newEnumMap(ResourceBlockTypes.class);
			defaultItemSettings = Maps.newEnumMap(ResourceItemTypes.class);
			defaultGenerationSettings = Maps.newEnumMap(ResourceBlockTypes.class);
			specialBlocks = Maps.newEnumMap(ResourceBlockTypes.class);
			specialItems = Maps.newEnumMap(ResourceItemTypes.class);
		}
		
		public Builder setCommonRarity(Rarity commonRarity) {
			this.commonRarity = commonRarity;
			return this;
		}
		
		public Builder disableOres() {
			ores = false;
			return this;
		}
		
		public Builder setConfig(ResourceBlockTypes type, ResourceBlockConfig config) {
			defaultBlockSettings.put(type, config);
			return this;
		}
		
		public Builder setConfig(ResourceItemTypes type, ResourceItemConfig config) {
			defaultItemSettings.put(type, config);
			return this;
		}
		
		public Builder setBlock(ResourceBlockTypes type, TriFunction<IResource, IResourceBlockType, IResourceBlockConfig, Block> function) {
			specialBlocks.put(type, function);
			return this;
		}
		
		public Builder setItem(ResourceItemTypes type, TriFunction<IResource, IResourceItemType, IResourceItemConfig, Item> function) {
			specialItems.put(type, function);
			return this;
		}
		
		public Builder setGeneration(ResourceBlockTypes type, ResourceGenerationConfig config) {
			defaultGenerationSettings.put(type, config);
			return this;
		}
		
		public Builder setArmor(UArmorMaterial material) {
			armor = true;
			defaultArmorMaterial = material;
			return this;
		}
		
		public Builder setTools(UToolMaterial material) {
			tools = true;
			defaultToolMaterial = material;
			return this;
		}
		
		public Resource build() {
			final List<ResourceBlockTypes> blockTypes = new ArrayList<>(ResourceBlockTypes.VALUES);
			final List<ResourceItemTypes> itemTypes = new ArrayList<>(ResourceItemTypes.VALUES);
			
			removeUnwanted(blockTypes, itemTypes);
			validateDefaultConfigs(blockTypes, itemTypes);
			
			blockTypes.forEach(type -> defaultBlockSettings.putIfAbsent(type, new ResourceBlockConfig(commonRarity, commonHardness, commonHardness, commonHarvestLevel)));
			itemTypes.forEach(type -> defaultItemSettings.putIfAbsent(type, new ResourceItemConfig(commonRarity)));
			
			final Map<ResourceBlockTypes, ResourceBlockConfig> blockSettings = Maps.newEnumMap(ResourceBlockTypes.class);
			final Map<ResourceItemTypes, ResourceItemConfig> itemSettings = Maps.newEnumMap(ResourceItemTypes.class);
			final Map<ResourceBlockTypes, ResourceGenerationConfig> generationConfig = Maps.newEnumMap(ResourceBlockTypes.class);
			
			final UArmorMaterial armorMaterial = armor ? loadArmorConfig() : null;
			final UToolMaterial toolMaterial = tools ? loadToolsConfig() : null;
			
			loadConfig(blockSettings, itemSettings);
			loadGenerationConfig(generationConfig);
			
			final Map<ResourceBlockTypes, Block> blocks = Maps.newEnumMap(ResourceBlockTypes.class);
			final Map<ResourceItemTypes, Item> items = Maps.newEnumMap(ResourceItemTypes.class);
			
			final Resource resource = new Resource(name, blocks, items, generationConfig, armorMaterial, toolMaterial);
			
			specialBlocks.forEach((type, function) -> blocks.put(type, function.apply(resource, type, blockSettings.get(type))));
			blockTypes.forEach(type -> blocks.putIfAbsent(type, type.createBlock(resource, blockSettings.get(type))));
			
			specialItems.forEach((type, function) -> items.put(type, function.apply(resource, type, itemSettings.get(type))));
			itemTypes.forEach(type -> items.putIfAbsent(type, type.createItem(resource, itemSettings.get(type))));
			
			// Validate if special blocks or items have not added some items that are not declared in the config
			if (itemTypes.size() != items.size() || blockTypes.size() != blocks.size()) {
				throw new IllegalStateException("You cannot add elements that you have not declared to be valid elements.");
			}
			
			return resource;
		}
		
		private void removeUnwanted(List<ResourceBlockTypes> blockTypes, List<ResourceItemTypes> itemTypes) {
			if (!ores) {
				blockTypes.remove(ResourceBlockTypes.ORE);
				blockTypes.remove(ResourceBlockTypes.NETHER_ORE);
			}
			if (!armor) {
				itemTypes.remove(ResourceItemTypes.HELMET);
				itemTypes.remove(ResourceItemTypes.CHESTPLATE);
				itemTypes.remove(ResourceItemTypes.LEGGINGS);
				itemTypes.remove(ResourceItemTypes.BOOTS);
			}
			if (!tools) {
				itemTypes.remove(ResourceItemTypes.AXE);
				itemTypes.remove(ResourceItemTypes.HOE);
				itemTypes.remove(ResourceItemTypes.PICKAXE);
				itemTypes.remove(ResourceItemTypes.SPADE);
				itemTypes.remove(ResourceItemTypes.SWORD);
			}
		}
		
		private void validateDefaultConfigs(List<ResourceBlockTypes> blockTypes, List<ResourceItemTypes> itemTypes) {
			removeIfNotInValidTypes(blockTypes, defaultBlockSettings.keySet());
			removeIfNotInValidTypes(itemTypes, defaultItemSettings.keySet());
			removeIfNotInValidTypes(blockTypes, defaultGenerationSettings.keySet());
		}
		
		private <T> void removeIfNotInValidTypes(List<T> validTypes, Collection<T> defaultSettings) {
			Iterator<T> iterator = defaultSettings.iterator();
			while (iterator.hasNext()) {
				if (!validTypes.contains(iterator.next())) {
					iterator.remove();
				}
			}
		}
		
		private void loadConfig(Map<ResourceBlockTypes, ResourceBlockConfig> blockSettings, Map<ResourceItemTypes, ResourceItemConfig> itemSettings) {
			final Path resourcePath = CONFIG_PATH.resolve("resources").resolve(name);
			
			defaultBlockSettings.forEach((type, defaultConfig) -> {
				try {
					blockSettings.put(type, ConfigUtil.loadConfig(resourcePath, type.getName(), GSON, writer -> {
						GSON.toJson(defaultConfig, ResourceBlockConfig.class, writer);
						return defaultConfig;
					}, reader -> {
						ResourceBlockConfig config = GSON.fromJson(reader, ResourceBlockConfig.class);
						config.validate();
						return config;
					}));
				} catch (IOException ex) {
					CONFIG_EXCEPTION_WITH_TYPE.apply(ex, name, type.getName());
				}
			});
			defaultItemSettings.forEach((type, defaultConfig) -> {
				try {
					itemSettings.put(type, ConfigUtil.loadConfig(resourcePath, type.getName(), GSON, writer -> {
						GSON.toJson(defaultConfig, ResourceItemConfig.class, writer);
						return defaultConfig;
					}, reader -> {
						ResourceItemConfig config = GSON.fromJson(reader, ResourceItemConfig.class);
						config.validate();
						return config;
					}));
				} catch (IOException ex) {
					CONFIG_EXCEPTION_WITH_TYPE.apply(ex, name, type.getName());
				}
			});
		}
		
		private void loadGenerationConfig(Map<ResourceBlockTypes, ResourceGenerationConfig> generationSettings) {
			final Path resourcePath = CONFIG_PATH.resolve("worldgen").resolve(name);
			
			defaultGenerationSettings.forEach((type, defaultConfig) -> {
				try {
					generationSettings.put(type, ConfigUtil.loadConfig(resourcePath, type.getName(), GSON, writer -> {
						GSON.toJson(defaultConfig, ResourceGenerationConfig.class, writer);
						return defaultConfig;
					}, reader -> {
						ResourceGenerationConfig config = GSON.fromJson(reader, ResourceGenerationConfig.class);
						config.validate();
						return config;
					}));
				} catch (IOException ex) {
					CONFIG_EXCEPTION_WITH_TYPE.apply(ex, name, type.getName());
				}
			});
		}
		
		private UArmorMaterial loadArmorConfig() {
			final Path resourcePath = CONFIG_PATH.resolve("material").resolve(name);
			
			try {
				return ConfigUtil.loadConfig(resourcePath, "armor", GSON, writer -> {
					GSON.toJson(defaultArmorMaterial, UArmorMaterial.class, writer);
					return defaultArmorMaterial;
				}, reader -> {
					UArmorMaterial material = GSON.fromJson(reader, UArmorMaterial.class);
					System.out.println(material.getDurability(EquipmentSlotType.HEAD));
					System.out.println(material.getDurability(EquipmentSlotType.CHEST));
					System.out.println(material.getDurability(EquipmentSlotType.LEGS));
					System.out.println(material.getDurability(EquipmentSlotType.FEET));
					
					System.out.println(material.getDamageReductionAmount(EquipmentSlotType.HEAD));
					System.out.println(material.getDamageReductionAmount(EquipmentSlotType.CHEST));
					System.out.println(material.getDamageReductionAmount(EquipmentSlotType.LEGS));
					System.out.println(material.getDamageReductionAmount(EquipmentSlotType.FEET));
					
					System.out.println(material.getEnchantability());
					
					System.out.println(material.getSoundEvent());
					
					System.out.println(material.getToughness());
					return material;
				});
			} catch (IOException ex) {
				CONFIG_EXCEPTION.apply(ex, name);
				return null;
			}
		}
		
		private UToolMaterial loadToolsConfig() {
			final Path resourcePath = CONFIG_PATH.resolve("material").resolve(name);
			
			try {
				return ConfigUtil.loadConfig(resourcePath, "tools", GSON, writer -> {
					GSON.toJson(defaultToolMaterial, UToolMaterial.class, writer);
					return defaultToolMaterial;
				}, reader -> {
					UToolMaterial material = GSON.fromJson(reader, UToolMaterial.class);
					
					System.out.println(material.getAdditionalDamage(Tools.AXE));
					System.out.println(material.getAdditionalDamage(Tools.HOE));
					System.out.println(material.getAdditionalDamage(Tools.PICKAXE));
					System.out.println(material.getAdditionalDamage(Tools.SPADE));
					System.out.println(material.getAdditionalDamage(Tools.SPADE));
					
					System.out.println(material.getAttackSpeed(Tools.AXE));
					System.out.println(material.getAttackSpeed(Tools.HOE));
					System.out.println(material.getAttackSpeed(Tools.PICKAXE));
					System.out.println(material.getAttackSpeed(Tools.SPADE));
					System.out.println(material.getAttackSpeed(Tools.SPADE));
					
					System.out.println(material.getHarvestLevel());
					
					System.out.println(material.getMaxUses());
					
					System.out.println(material.getEfficiency());
					
					System.out.println(material.getAttackDamage());
					
					System.out.println(material.getEnchantability());
					
					return material;
				});
			} catch (IOException ex) {
				CONFIG_EXCEPTION.apply(ex, name);
				return null;
			}
		}
	}
}
