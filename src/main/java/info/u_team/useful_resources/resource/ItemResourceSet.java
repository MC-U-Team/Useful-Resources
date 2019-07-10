package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.item.ResourceItem;
import net.minecraft.item.Item;

public class ItemResourceSet implements IResourceItems {
	
	private final Item ingot;
	private final Item nugget;
	private final Item dust;
	private final Item plate;
	private final Item densePlate;
	private final Item gear;
	private final Item stick;
	
	public ItemResourceSet(IResource resource) {
		ingot = new ResourceItem("ingot", resource);
		nugget = new ResourceItem("nugget", resource);
		dust = new ResourceItem("dust", resource);
		plate = new ResourceItem("plate", resource);
		densePlate = new ResourceItem("dense_plate", resource);
		gear = new ResourceItem("gear", resource);
		stick = new ResourceItem("stick", resource);
	}
	
	@Override
	public Item getIngot() {
		return ingot;
	}
	
	@Override
	public Item getNugget() {
		return nugget;
	}
	
	@Override
	public Item getDust() {
		return dust;
	}
	
	@Override
	public Item getPlate() {
		return plate;
	}
	
	@Override
	public Item getDensePlate() {
		return densePlate;
	}
	
	@Override
	public Item getGear() {
		return gear;
	}
	
	@Override
	public Item getStick() {
		return stick;
	}
}
