package info.u_team.useful_resources.resource.builder;

import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.resource.AbstractResourceBuilder;
import info.u_team.useful_resources.resource.feature.OreResourceFeature;
import net.minecraft.world.item.Rarity;

public class BasicOreResourceBuilder extends AbstractResourceBuilder {
	
	private float baseDestroyTime = 3;
	
	public BasicOreResourceBuilder(String name, int color, Rarity rarity) {
		super(name, color, rarity);
	}
	
	public BasicOreResourceBuilder baseDestroyTime(float baseDestroyTime) {
		this.baseDestroyTime = baseDestroyTime;
		return this;
	}
	
	@Override
	protected void apply(AbstractResourceEntries entries) {
		add(OreResourceFeature.createStoneOre(baseDestroyTime, baseDestroyTime));
		add(OreResourceFeature.createDeepslateOre(baseDestroyTime, baseDestroyTime));
		add(OreResourceFeature.createNetherrackOre(baseDestroyTime, baseDestroyTime));
		add(OreResourceFeature.createEndstoneOre(baseDestroyTime, baseDestroyTime));
	}
	
}
