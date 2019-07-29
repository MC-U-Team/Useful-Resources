package info.u_team.useful_resources.resource;

import java.util.Map;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.item.armor.UArmorMaterialVanilla;
import info.u_team.u_team_core.item.tool.UToolMaterial;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.type.ResourceItemTypes;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvents;

public class ResourceItems implements IResourceItems {
	
	private final IResource resource;
	
	private final Map<ResourceItemTypes, Item> itemMap;
	
	public ResourceItems(IResource resource, Map<ResourceItemTypes, Item> itemMap) {
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
	public IArmorMaterial getArmorMaterial() {
		return new UArmorMaterialVanilla(10, new int[] { 2, 3, 4, 3 }, 20, SoundEvents.AMBIENT_CAVE, 1.0F, () -> Ingredient.fromItems(getItem(ResourceItemTypes.INGOT)));
	}
	
	@Override
	public boolean hasArmorMaterial() {
		return true;
	}
	
	@Override
	public IToolMaterial getToolMaterial() {
		return new UToolMaterial(new int[] { 2, 1, 2, 1, 1 }, new float[] { 1, 2, 0, 3, -1 }, 2, 10, 20, 5, 20, () -> Ingredient.fromItems(getItem(ResourceItemTypes.INGOT)));
	}
	
	@Override
	public boolean hasToolMaterial() {
		return true;
	}
	
	@Override
	public Item[] getArray() {
		return itemMap.values().stream().toArray(Item[]::new);
	}
}
