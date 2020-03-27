package info.u_team.useful_resources.api.resource;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;

import java.util.*;
import java.util.function.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.api.registry.IUBlockRegistryType;
import info.u_team.useful_resources.api.material.*;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.storage.loot.LootTable;

public abstract class BasicResource<T extends BasicResource<T>> extends Resource {
	
	private final Rarity rarity;
	
	private final Map<String, WorldGenFeature> worldGenFeatures;
	private final Map<BlockResourceType, LootTable> extraLootTables;
	private final Map<String, Object> extraProperties;
	
	private final IDataGeneratorConfigurator dataGeneratorConfigurator;
	
	public BasicResource(String name, int color, ItemResourceType repairType, Rarity rarity) {
		super(name, color, repairType);
		this.rarity = rarity;
		worldGenFeatures = new HashMap<>();
		extraLootTables = new HashMap<>();
		extraProperties = new HashMap<>();
		dataGeneratorConfigurator = new IDataGeneratorConfigurator() {
			
			@Override
			public Map<String, WorldGenFeature> getWorldGeneration() {
				return worldGenFeatures;
			}
			
			@Override
			public Map<BlockResourceType, LootTable> getExtraLootTables() {
				return extraLootTables;
			}
			
			@Override
			public Map<String, Object> getExtraProperties() {
				return extraProperties;
			}
		};
	}
	
	@Override
	public IDataGeneratorConfigurator getDataGeneratorConfigurator() {
		return dataGeneratorConfigurator;
	}
	
	@Override
	public void clearDataGeneratorConfig() {
		worldGenFeatures.clear();
		extraLootTables.clear();
		extraProperties.clear();
	}
	
	public T setTools(IToolMaterial toolMaterial) {
		addFeature(createTools(rarity, new WrappedToolMaterial(toolMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType())))));
		return getThis();
	}
	
	public T setArmor(IArmorMaterial armorMaterial) {
		addFeature(createArmor(rarity, new WrappedArmorMaterial(armorMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType())))));
		return getThis();
	}
	
	public T setHorseArmor(int armorPoints) {
		addFeature(createHorseArmor(rarity, armorPoints));
		return getThis();
	}
	
	public T setExisting(BlockResourceType type, Block block) {
		addFeature(addExistingBlock(type, block));
		return getThis();
	}
	
	public T setExisting(FluidResourceType type, Fluid fluid) {
		addFeature(addExistingFluid(type, fluid));
		return getThis();
	}
	
	public T setExisting(ItemResourceType type, Item item) {
		addFeature(addExistingItem(type, item));
		return getThis();
	}
	
	public T setGenerationDefault(BlockResourceType type, Function<BlockState, WorldGenFeature> function) {
		return setGeneration(type, block -> function.apply(block.getDefaultState()));
	}
	
	public T setGeneration(BlockResourceType type, Function<Block, WorldGenFeature> function) {
		return setGeneration(type.getName(), function.apply(getBlocks().get(type)));
	}
	
	private T setGeneration(String name, WorldGenFeature feature) {
		worldGenFeatures.put(name, feature);
		return getThis();
	}
	
	public T setLootTableWithFortune(BlockResourceType type, ItemResourceType dropType, BiFunction<Item, Item, LootTable> function) {
		return setLootTable(type, item -> function.apply(item, getItems().get(dropType)));
	}
	
	public T setLootTable(BlockResourceType type, Function<Item, LootTable> function) {
		final Block block = getBlocks().get(type);
		final Item item;
		if (block instanceof IUBlockRegistryType) {
			item = ((IUBlockRegistryType) block).getBlockItem();
		} else {
			item = block.asItem();
		}
		return setLootTable(type, function.apply(item));
	}
	
	public T setLootTable(BlockResourceType type, LootTable lootTable) {
		// extraLootTables.put(type, lootTable);
		return getThis();
	}
	
	public T setProperty(String key, Object value) {
		extraProperties.put(key, value);
		return getThis();
	}
	
	@SuppressWarnings("unchecked")
	private T getThis() {
		return (T) this;
	}
	
}
