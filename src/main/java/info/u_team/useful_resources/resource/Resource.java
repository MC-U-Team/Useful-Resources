package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import net.minecraft.world.item.Rarity;

public class Resource implements AbstractResource {
	
	private final String name;
	private final int color;
	private final Rarity rarity;
	
	private final AbstractResourceEntries entries;
	
	protected Resource(String name, int color, Rarity rarity, AbstractResourceEntries entries) {
		this.name = name;
		this.color = color;
		this.rarity = rarity;
		this.entries = entries;
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
	public Rarity getRarity() {
		return rarity;
	}
	
	@Override
	public AbstractResourceEntries getEntries() {
		return entries;
	}
	
}
