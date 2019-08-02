package info.u_team.useful_resources.config;

import com.google.gson.annotations.SerializedName;

import info.u_team.useful_resources.api.resource.config.IResourceBlockConfig;
import net.minecraft.item.Rarity;

public class ResourceBlockConfig implements IResourceBlockConfig {
	
	private Rarity rarity;
	private float hardness;
	private float resistance;
	@SerializedName("harvest_level")
	private int harvestLevel;
	
	public ResourceBlockConfig(float hardness, int harvestLevel) {
		this(Rarity.COMMON, hardness, hardness, harvestLevel);
	}
	
	public ResourceBlockConfig(float hardness, float resistance, int harvestLevel) {
		this(Rarity.COMMON, hardness, resistance, harvestLevel);
	}
	
	public ResourceBlockConfig(Rarity rarity, float hardness, float resistance, int harvestLevel) {
		this.rarity = rarity;
		this.hardness = hardness;
		this.resistance = resistance;
		this.harvestLevel = harvestLevel;
	}
	
	@Override
	public Rarity getRarity() {
		return rarity;
	}
	
	@Override
	public float getHardness() {
		return hardness;
	}
	
	@Override
	public float getResistance() {
		return resistance;
	}
	
	@Override
	public int getHarvestLevel() {
		return harvestLevel;
	}
	
	public void validate() {
		if (rarity == null) {
			throw new IllegalStateException("Rarity cannot be null");
		}
	}
}
