package info.u_team.useful_resources.resource.common;

import info.u_team.useful_resources.resource.builder.BasicOreResourceBuilder;
import net.minecraft.world.item.Rarity;

public class CommonResourceBuilder {
	
	public static BasicOreResourceBuilder basicOre(String name, int color, Rarity rarity) {
		return new BasicOreResourceBuilder(name, color, rarity);
	}
	
	private CommonResourceBuilder() {
	}
	
}
