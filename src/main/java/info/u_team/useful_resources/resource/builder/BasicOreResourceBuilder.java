package info.u_team.useful_resources.resource.builder;

import java.util.function.Consumer;

import info.u_team.useful_resources.resource.AbstractResourceBuilder;
import info.u_team.useful_resources.resource.ResourceEntries;
import info.u_team.useful_resources.resource.ResourceFeature;

public class BasicOreResourceBuilder extends AbstractResourceBuilder {
	
	public BasicOreResourceBuilder(String name, int color, Consumer<ExistingResourceTypes> existingTypes) {
		super(name, color, existingTypes);
	}
	
	@Override
	protected void apply(ResourceEntries entries) {
		add((properties, registerProvider) -> {
			return new ResourceFeature();
		});
	}
	
}
