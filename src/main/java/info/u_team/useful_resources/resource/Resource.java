package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;

public class Resource implements AbstractResource {
	
	private final String name;
	private final int color;
	
	private final AbstractResourceEntries entries;
	private final AbstractRegisterProvider registerProvider;
	
	protected Resource(String name, int color, AbstractResourceEntries entries, AbstractRegisterProvider registerProvider) {
		this.name = name;
		this.color = color;
		this.entries = entries;
		this.registerProvider = registerProvider;
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
	
	@Override
	public AbstractRegisterProvider getRegisterProvider() {
		return registerProvider;
	}
	
}
