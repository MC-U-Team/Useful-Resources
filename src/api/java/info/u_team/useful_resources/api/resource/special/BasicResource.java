package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;

import java.util.*;
import java.util.function.Function;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.useful_resources.api.material.*;
import info.u_team.useful_resources.api.resource.Resource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;

public abstract class BasicResource<T extends BasicResource<T>> extends Resource {
	
	private final Rarity rarity;
	
	private final Map<String, WorldGenFeature> worldGenFeatures;
	private final Map<String, Object> extraProperties;
	
	private final IDataGeneratorConfigurator dataGeneratorConfigurator;
	
	public BasicResource(String name, int color, ItemResourceType repairType, Rarity rarity) {
		super(name, color, repairType);
		this.rarity = rarity;
		worldGenFeatures = new HashMap<>();
		extraProperties = new HashMap<>();
		dataGeneratorConfigurator = new IDataGeneratorConfigurator() {
			
			@Override
			public Map<String, WorldGenFeature> getWorldGeneration() {
				return worldGenFeatures;
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
	
	@SuppressWarnings("unchecked")
	private T getThis() {
		return (T) this;
	}
	
}
