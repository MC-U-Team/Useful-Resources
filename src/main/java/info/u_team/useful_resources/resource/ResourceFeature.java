package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;

public class ResourceFeature implements AbstractResourceFeature {
	
	private final ResourceEntries entries;
	
	public ResourceFeature() {
		entries = new ResourceEntries();
	}
	
	@Override
	public AbstractResourceEntries getEntries() {
		return entries;
	}
	
}
