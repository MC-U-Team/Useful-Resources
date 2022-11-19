package info.u_team.useful_resources.resource.common;

import java.util.function.Consumer;

import info.u_team.useful_resources.resource.AbstractResourceBuilder.ExistingResourceTypes;
import info.u_team.useful_resources.resource.builder.BasicOreResourceBuilder;
import net.minecraft.world.item.Rarity;

public class CommonResourceBuilder {
	
	public static BasicOreResourceBuilder basicOre(String name, int color, Rarity rarity, Consumer<ExistingResourceTypes> existingTypes) {
		return new BasicOreResourceBuilder(name, color, rarity, existingTypes);
	}
	
	// Utility methods
	
	public static Consumer<ExistingResourceTypes> noExisting() {
		return (existingTypes) -> {
		};
	}
	
	private CommonResourceBuilder() {
	}
	
}
