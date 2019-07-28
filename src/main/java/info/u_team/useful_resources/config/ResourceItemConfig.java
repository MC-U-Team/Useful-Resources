package info.u_team.useful_resources.config;

import info.u_team.useful_resources.api.config.IResourceItemConfig;
import net.minecraft.item.Rarity;

public class ResourceItemConfig implements IResourceItemConfig {
	
	private Rarity rarity;
	
	public ResourceItemConfig() {
		this(Rarity.COMMON);
	}
	
	public ResourceItemConfig(Rarity rarity) {
		this.rarity = rarity;
	}
	
	@Override
	public Rarity getRarity() {
		return rarity;
	}
	
	public void validate() {
		if (rarity == null) {
			throw new IllegalStateException("Rarity cannot be null");
		}
	}
}
