package info.u_team.useful_resources.resource;

import java.util.function.Supplier;

import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.datagen.AbstractResourceDataGenInfo;
import net.minecraft.world.item.Rarity;

public class Resource implements AbstractResource {
	
	private final String name;
	private final int color;
	private final Rarity rarity;
	
	private final AbstractResourceEntries entries;
	
	private final Supplier<AbstractResourceDataGenInfo> dataGenInfo;
	
	protected Resource(String name, int color, Rarity rarity, AbstractResourceEntries entries, Supplier<AbstractResourceDataGenInfo> dataGenInfo) {
		this.name = name;
		this.color = color;
		this.rarity = rarity;
		this.entries = entries;
		this.dataGenInfo = dataGenInfo;
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
	
	@Override
	public Supplier<AbstractResourceDataGenInfo> getDataGenInfo() {
		return dataGenInfo;
	}
	
}
