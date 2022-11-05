package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.Resource;

public class ResourceRegistry {
	
	static <T extends Resource> T register(T resource) {
		return resource;
	}
	
}
