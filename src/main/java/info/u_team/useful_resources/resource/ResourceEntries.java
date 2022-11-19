package info.u_team.useful_resources.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ResourceEntries implements AbstractResourceEntries {
	
	private final Map<ResourceTypeKey<Block>, RegistryEntry<? extends Block>> blocks;
	private final Map<ResourceTypeKey<Fluid>, RegistryEntry<? extends Fluid>> fluids;
	private final Map<ResourceTypeKey<Item>, RegistryEntry<? extends Item>> items;
	
	public ResourceEntries() {
		this.blocks = new HashMap<>();
		this.fluids = new HashMap<>();
		this.items = new HashMap<>();
	}
	
	@Override
	public Map<ResourceTypeKey<Block>, RegistryEntry<? extends Block>> getBlocks() {
		return blocks;
	}
	
	@Override
	public Map<ResourceTypeKey<Fluid>, RegistryEntry<? extends Fluid>> getFluids() {
		return fluids;
	}
	
	@Override
	public Map<ResourceTypeKey<Item>, RegistryEntry<? extends Item>> getItems() {
		return items;
	}
	
	@Override
	public AbstractResourceEntries toImmutable() {
		return new AbstractResourceEntries() {
			
			@Override
			public Map<ResourceTypeKey<Block>, RegistryEntry<? extends Block>> getBlocks() {
				return Collections.unmodifiableMap(blocks);
			}
			
			@Override
			public Map<ResourceTypeKey<Fluid>, RegistryEntry<? extends Fluid>> getFluids() {
				return Collections.unmodifiableMap(fluids);
			}
			
			@Override
			public Map<ResourceTypeKey<Item>, RegistryEntry<? extends Item>> getItems() {
				return Collections.unmodifiableMap(items);
			}
			
			@Override
			public AbstractResourceEntries toImmutable() {
				return this;
			}
		};
	}
	
	public void merge(AbstractResourceEntries entries) {
		mergeSingleEntryMap(blocks, entries.getBlocks());
		mergeSingleEntryMap(fluids, entries.getFluids());
		mergeSingleEntryMap(items, entries.getItems());
	}
	
	private <T> void mergeSingleEntryMap(Map<ResourceTypeKey<T>, RegistryEntry<? extends T>> baseMap, Map<ResourceTypeKey<T>, RegistryEntry<? extends T>> map) {
		map.entrySet().stream().peek(entry -> {
			if (baseMap.containsKey(entry.getKey())) {
				throw new IllegalStateException("Cannot merge resource entries with this map, because " + entry.getKey() + " already exists.");
			}
		}).forEach(entry -> baseMap.put(entry.getKey(), entry.getValue()));
	}
	
	public void addBlock(ResourceTypeKey<Block> type, RegistryEntry<? extends Block> entry) {
		blocks.put(type, entry);
	}
	
	public void addFluid(ResourceTypeKey<Fluid> type, RegistryEntry<? extends Fluid> entry) {
		fluids.put(type, entry);
	}
	
	public void addItem(ResourceTypeKey<Item> type, RegistryEntry<? extends Item> entry) {
		items.put(type, entry);
	}
	
}
