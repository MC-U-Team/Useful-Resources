package info.u_team.useful_resources.resource.common.impl;

import java.util.function.Consumer;

import info.u_team.useful_resources.resource.AbstractResourceBuilder;

public class BasicOreResourceBuilder extends AbstractResourceBuilder {
	
	public BasicOreResourceBuilder(String name, int color, Consumer<ExistingResourceTypes> existingTypes) {
		super(name, color, existingTypes);
	}
	
	@Override
	protected void apply(String name, int color) {
		
	}
	
}
