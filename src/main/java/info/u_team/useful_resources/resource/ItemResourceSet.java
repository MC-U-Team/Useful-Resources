package info.u_team.useful_resources.resource;

import java.util.Map;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.ResourceItemTypes;
import net.minecraft.item.Item;

public class ItemResourceSet implements IResourceItems {
	
	private final IResource resource;
	
	private final Map<ResourceItemTypes, Item> itemMap;
	
	public ItemResourceSet(IResource resource, Map<ResourceItemTypes, Item> itemMap) {
		this.resource = resource;
		this.itemMap = itemMap;
	}
	
	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public Item getItem(IResourceItemType type) {
		return itemMap.get(type);
	}
	
	@Override
	public boolean hasItem(IResourceItemType type) {
		return itemMap.containsKey(type);
	}
	
	@Override
	public Item[] getArray() {
		return itemMap.values().stream().toArray(Item[]::new);
	}
}
