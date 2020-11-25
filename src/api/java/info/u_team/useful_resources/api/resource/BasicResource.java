package info.u_team.useful_resources.api.resource;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;

import java.util.*;
import java.util.function.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.u_team_core.util.CastUtil;
import info.u_team.useful_resources.api.material.*;
import info.u_team.useful_resources.api.resource.data.*;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.worldgen.WorldGenFeatures;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.LootTable;

public abstract class BasicResource<T extends BasicResource<T>> extends Resource {
	
	private final Rarity rarity;
	
	private final Map<String, Supplier<WorldGenFeatures>> worldGenFeatures;
	private final Map<BlockResourceType, Supplier<LootTable>> extraLootTables;
	private final Map<String, Object> extraProperties;
	
	private final IDataGeneratorConfigurator dataGeneratorConfigurator;
	
	public BasicResource(String name, int color, ItemResourceType repairType, Rarity rarity, ResourceType type) {
		super(name, color, repairType);
		this.rarity = rarity;
		worldGenFeatures = new HashMap<>();
		extraLootTables = new HashMap<>();
		extraProperties = new HashMap<>();
		dataGeneratorConfigurator = new DataGeneratorConfigurator(type, worldGenFeatures, extraLootTables, extraProperties);
	}
	
	@Override
	public IDataGeneratorConfigurator getDataGeneratorConfigurator() {
		return dataGeneratorConfigurator;
	}
	
	public T setTools(IToolMaterial toolMaterial) {
		addFeature(createTools(rarity, new WrappedToolMaterial(toolMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType()).get()))));
		return getThis();
	}
	
	public T setArmor(IArmorMaterial armorMaterial) {
		addFeature(createArmor(rarity, new WrappedArmorMaterial(armorMaterial, () -> Ingredient.fromItems(getItems().get(getRepairType()).get()))));
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
	
	public T setGenerationDefault(BlockResourceType type, Function<BlockState, WorldGenFeatures> function) {
		return setGeneration(type, block -> function.apply(block.getDefaultState()));
	}
	
	public T setGeneration(BlockResourceType type, Function<Block, WorldGenFeatures> function) {
		return setGeneration(type.getName(), () -> function.apply(getBlocks().get(type).get()));
	}
	
	private T setGeneration(String name, Supplier<WorldGenFeatures> feature) {
		worldGenFeatures.put(name, feature);
		return getThis();
	}
	
	public T setLootTableWithFortune(BlockResourceType type, ItemResourceType dropType, BiFunction<Item, Item, LootTable> function) {
		return setLootTable(type, item -> function.apply(item, getItems().get(dropType).get()));
	}
	
	public T setLootTable(BlockResourceType type, Function<Item, LootTable> function) {
		return setLootTable(type, () -> {
			final Block block = getBlocks().get(type).get();
			final Item item;
			if (block instanceof IBlockItemProvider) {
				item = ((IBlockItemProvider) block).getBlockItem();
			} else {
				item = block.asItem();
			}
			return function.apply(item);
		});
	}
	
	public T setLootTable(BlockResourceType type, Supplier<LootTable> lootTable) {
		extraLootTables.put(type, lootTable);
		return getThis();
	}
	
	public T setProperty(String key, Object value) {
		extraProperties.put(key, value);
		return getThis();
	}
	
	private T getThis() {
		return CastUtil.uncheckedCast(this);
	}
	
}
