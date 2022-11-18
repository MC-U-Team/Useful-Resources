package info.u_team.useful_resources.resources;

import static info.u_team.useful_resources.resource.common.CommonResourceBuilder.basicOre;
import static info.u_team.useful_resources.resource.common.CommonResourceBuilder.noExisting;

import info.u_team.useful_resources.resource.Resource;

public class Resources {
	
	public static Resource SILVER = basicOre("silver", 0xFFFF00, noExisting()).build();
	
	private Resources() {
	}
}
