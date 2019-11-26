package info.u_team.useful_resources.resources;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.type.ItemResourceType;

public class Resources {
	
	public static final IResource COPPER = ResourceRegistry.register(new Resource("copper", 0xFFFFFF, ItemResourceType.INGOT));
	
}
