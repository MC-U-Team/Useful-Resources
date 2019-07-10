package info.u_team.useful_resources.resource;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import info.u_team.useful_resources.api.IResource;
import info.u_team.useful_resources.item.ResourceItem;
import net.minecraft.item.Item;

public class ItemResourceSet implements IUArrayRegistryType<Item> {
	
	private final Item[] resources;
	
	public ItemResourceSet(IResource resource) {
		resources = new Item[] { new ResourceItem("ingot", resource), new ResourceItem("nugget", resource), new ResourceItem("plate", resource), new ResourceItem("dense_plate", resource), new ResourceItem("gear", resource) };
	}
	
	@Override
	public Item[] getArray() {
		return resources;
	}
	
}
