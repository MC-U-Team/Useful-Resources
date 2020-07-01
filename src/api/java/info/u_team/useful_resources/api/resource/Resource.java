package info.u_team.useful_resources.api.resource;

import java.util.*;

import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.util.Cast;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class Resource implements IResource {
	
	private final String name;
	private final int color;
	private final ItemResourceType repairType;
	
	private final Map<BlockResourceType, RegistryEntry<Block>> blocks;
	private final Map<FluidResourceType, RegistryEntry<Fluid>> fluids;
	private final Map<ItemResourceType, RegistryEntry<Item>> items;
	
	private final List<RegistryEntry<Block>> registryBlocks;
	private final List<RegistryEntry<Fluid>> registryFluids;
	private final List<RegistryEntry<Item>> registryItems;
	
	public Resource(String name, int color, ItemResourceType repairType) {
		this.name = name;
		this.color = color;
		this.repairType = repairType;
		blocks = new EnumMap<>(BlockResourceType.class);
		fluids = new EnumMap<>(FluidResourceType.class);
		items = new EnumMap<>(ItemResourceType.class);
		registryBlocks = new ArrayList<>();
		registryFluids = new ArrayList<>();
		registryItems = new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getColor() {
		return color;
	}
	
	@Override
	public ItemResourceType getRepairType() {
		return repairType;
	}
	
	@Override
	public Map<BlockResourceType, RegistryEntry<Block>> getBlocks() {
		return Collections.unmodifiableMap(blocks);
	}
	
	@Override
	public Map<FluidResourceType, RegistryEntry<Fluid>> getFluids() {
		return Collections.unmodifiableMap(fluids);
	}
	
	@Override
	public Map<ItemResourceType, RegistryEntry<Item>> getItems() {
		return Collections.unmodifiableMap(items);
	}
	
	@Override
	public List<RegistryEntry<Block>> getRegistryBlocks() {
		return registryBlocks;
	}
	
	@Override
	public List<RegistryEntry<Fluid>> getRegistryFluids() {
		return registryFluids;
	}
	
	@Override
	public List<RegistryEntry<Item>> getRegistryItems() {
		return registryItems;
	}
	
	@Override
	public void addFeature(IResourceFeatureBuilder builder) {
		final IResourceFeature feature = builder.build(name);
		
		addEntriesToMap(blocks, feature.getBlocks(), registryBlocks, feature.getRegistryBlocks());
		addEntriesToMap(fluids, feature.getFluids(), registryFluids, feature.getRegistryFluids());
		addEntriesToMap(items, feature.getItems(), registryItems, feature.getRegistryItems());
	}
	
	private <K, V extends IForgeRegistryEntry<? super V>> void addEntriesToMap(Map<K, RegistryEntry<V>> baseMap, Map<K, RegistryEntry<? extends V>> map, List<RegistryEntry<V>> baseRegistryList, Collection<RegistryEntry<? extends V>> registryCollection) {
		map.entrySet().stream().peek(entry -> {
			if (baseMap.containsKey(entry.getKey())) {
				throw new IllegalStateException("Cannot add a feature with entries that already exist in the resource");
			}
		}).forEach(entry -> baseMap.put(entry.getKey(), Cast.uncheckedCast(entry.getValue())));
		registryCollection.stream().peek(value -> {
			if (!map.containsValue(value)) {
				throw new IllegalStateException("Cannot add a feature with registry entries that are not in the normal feature entry map");
			}
			if (baseRegistryList.contains(value)) {
				throw new IllegalStateException("Cannot add a feature with registry entries that already exist in the resource");
			}
		}).forEach(value -> baseRegistryList.add(Cast.uncheckedCast(value)));
	}
}
