package info.u_team.useful_resources.resource;

import java.util.Map;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.type.ResourceItemTypes;
import net.minecraft.item.*;

public class ResourceItems implements IResourceItems {
	
	private final IResource resource;
	
	private final Map<ResourceItemTypes, Item> itemMap;
	
	private final IArmorMaterial armorMaterial;
	private final IToolMaterial toolMaterial;
	
	public ResourceItems(IResource resource, Map<ResourceItemTypes, Item> itemMap, IArmorMaterial armorMaterial, IToolMaterial toolMaterial) {
		this.resource = resource;
		this.itemMap = itemMap;
		this.armorMaterial = armorMaterial;
		this.toolMaterial = toolMaterial;
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
	public IArmorMaterial getArmorMaterial() {
		return armorMaterial;
	}
	
	@Override
	public boolean hasArmorMaterial() {
		return armorMaterial != null;
	}
	
	@Override
	public IToolMaterial getToolMaterial() {
		return toolMaterial;
	}
	
	@Override
	public boolean hasToolMaterial() {
		return toolMaterial != null;
	}
	
	@Override
	public Item[] getArray() {
		return itemMap.values().stream().toArray(Item[]::new);
	}
}
