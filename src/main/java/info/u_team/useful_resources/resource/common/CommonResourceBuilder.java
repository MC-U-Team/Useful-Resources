package info.u_team.useful_resources.resource.common;

import java.util.function.Consumer;

import info.u_team.useful_resources.resource.AbstractResourceBuilder.ExistingResourceTypes;
import info.u_team.useful_resources.resource.builder.BasicOreResourceBuilder;

public class CommonResourceBuilder {
	
	public static BasicOreResourceBuilder basicOre(String name, int color, Consumer<ExistingResourceTypes> existingTypes) {
		return new BasicOreResourceBuilder(name, color, existingTypes);
	}
	
	// Utility methods
	
	public static Consumer<ExistingResourceTypes> noExisting() {
		return (existingTypes) -> {
		};
	}
	
	private CommonResourceBuilder() {
	}
	
}
