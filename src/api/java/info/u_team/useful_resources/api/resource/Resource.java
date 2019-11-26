package info.u_team.useful_resources.api.resource;

import java.util.*;

import info.u_team.useful_resources.api.IResource;
import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Resource implements IResource {
	
	private final String name;
	private final int color;
	private final ItemResourceType repairType;
	
	private final Map<BlockResourceType, Block> blocks;
	private final Map<ItemResourceType, Item> items;
	
	public Resource(String name, int color, ItemResourceType repairType) {
		this.name = name;
		this.color = color;
		this.repairType = repairType;
		blocks = new EnumMap<>(BlockResourceType.class);
		items = new EnumMap<>(ItemResourceType.class);
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
	public Map<BlockResourceType, Block> getBlocks() {
		return Collections.unmodifiableMap(blocks);
	}
	
	@Override
	public Map<ItemResourceType, Item> getItems() {
		return Collections.unmodifiableMap(items);
	}
	
	@Override
	public void addFeature(IResourceFeatureBuilder builder) {
		final IResourceFeature feature = builder.build(name);
		
		addEntriesToMap(blocks, feature.getBlocks());
		addEntriesToMap(items, feature.getItems());
	}
	
	private <K, V> void addEntriesToMap(Map<K, V> baseMap, Map<K, V> map) {
		map.entrySet().stream().peek(entry -> {
			if (blocks.containsKey(entry.getKey())) {
				throw new IllegalStateException("Cannot add a feature with entries that already exist in the resource");
			}
		}).forEach(entry -> baseMap.put(entry.getKey(), entry.getValue()));
	}
	
}
