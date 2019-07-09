package info.u_team.useful_ores.config;

import java.util.function.Supplier;

import info.u_team.useful_ores.api.IResourceConfig;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ResourceConfig implements IResourceConfig {
	
	private final ConfigValue<Float> oreHardness;
	private final ConfigValue<Float> oreResistance;
	private final ConfigValue<Float> blockHardness;
	private final ConfigValue<Float> blockResistance;
	
	public ResourceConfig(ConfigValue<Float> oreHardness, ConfigValue<Float> oreResistance, ConfigValue<Float> blockHardness, ConfigValue<Float> blockResistance) {
		this.oreHardness = oreHardness;
		this.oreResistance = oreResistance;
		this.blockHardness = blockHardness;
		this.blockResistance = blockResistance;
	}
	
	@Override
	public Supplier<Float> getOreHardness() {
		return () -> oreHardness.get();
	}
	
	@Override
	public Supplier<Float> getOreResistance() {
		return () -> oreResistance.get();
	}
	
	@Override
	public Supplier<Float> getBlockHardness() {
		return () -> blockHardness.get();
	}
	
	@Override
	public Supplier<Float> getBlockResistance() {
		return () -> blockResistance.get();
	}
	
}
