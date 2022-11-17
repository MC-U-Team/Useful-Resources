package info.u_team.useful_resources.resource.common;

import info.u_team.useful_resources.resource.ResourceBuilder;

public class BasicOreResourceBuilder extends ResourceBuilder {
	
	protected BasicOreResourceBuilder(String name, int color) {
		super(name, color);
	}
	
	@Override
	protected void apply() {
		addFeature();
	}
	
}
