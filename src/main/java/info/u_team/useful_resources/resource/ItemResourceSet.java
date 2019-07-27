package info.u_team.useful_resources.resource;

import java.util.EnumMap;

import com.google.common.collect.Maps;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.ResourceItemTypes;
import net.minecraft.item.Item;

public class ItemResourceSet implements IResourceItems {
	
	private final IResource resource;
	
	private final EnumMap<ResourceItemTypes, Item> itemMap;
	
	public ItemResourceSet(IResource resource) {
		this.resource = resource;
		itemMap = Maps.newEnumMap(ResourceItemTypes.class);
		ResourceItemTypes.VALUES.forEach(type -> itemMap.put(type, type.createItem(resource)));
	}
	
	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public Item getItem(IResourceItemTypes type) {
		return itemMap.get(type);
	}
	
	@Override
	public boolean hasItem(IResourceItemTypes type) {
		return itemMap.containsKey(type);
	}
	
	@Override
	public Item[] getArray() {
		return itemMap.values().stream().toArray(Item[]::new);
	}
}
