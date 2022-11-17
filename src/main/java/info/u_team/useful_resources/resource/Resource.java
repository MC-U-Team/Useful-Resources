package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;

public class Resource implements AbstractResource {
	
	private final String name;
	private final int color;
	
	private final ResourceEntries entries;
	
	protected Resource(String name, int color, ResourceEntries entries) {
		this.name = name;
		this.color = color;
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
	public AbstractResourceEntries getEntries() {
		return entries;
	}
	
}
