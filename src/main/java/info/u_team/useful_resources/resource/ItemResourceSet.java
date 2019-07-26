package info.u_team.useful_resources.resource;

import java.util.EnumMap;

import com.google.common.collect.Maps;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.ItemResourceTypes;
import net.minecraft.item.Item;

public class ItemResourceSet implements IResourceItems {
	
	private final IResource resource;
	
	private final EnumMap<ItemResourceTypes, Item> itemMap;
	
	public ItemResourceSet(IResource resource) {
		this.resource = resource;
		itemMap = Maps.newEnumMap(ItemResourceTypes.class);
		ItemResourceTypes.VALUES.forEach(type -> itemMap.put(type, type.createItem(resource)));
	}
	
	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public Item getItem(IItemResourceTypes type) {
		return itemMap.get(type);
	}
	
	@Override
	public boolean hasItem(IItemResourceTypes type) {
		return itemMap.containsKey(type);
	}
	
	@Override
	public Item[] getArray() {
		return itemMap.values().stream().toArray(Item[]::new);
	}
}
