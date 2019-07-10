package info.u_team.useful_resources.config;

import java.util.function.Supplier;

import info.u_team.useful_resources.api.*;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ResourceConfig implements IResourceConfig {
	
	private final ConfigValue<Float> oreHardness;
	private final ConfigValue<Float> oreResistance;
	private final ConfigValue<Float> netherOreHardness;
	private final ConfigValue<Float> netherOreResistance;
	private final ConfigValue<Float> blockHardness;
	private final ConfigValue<Float> blockResistance;
	
	private final Supplier<IGeneratable> oreGeneratable;
	private final Supplier<IGeneratable> netherOreGeneratable;
	
	public ResourceConfig(ConfigValue<Float> oreHardness, ConfigValue<Float> oreResistance, ConfigValue<Float> netherOreHardness, ConfigValue<Float> netherOreResistance, ConfigValue<Float> blockHardness, ConfigValue<Float> blockResistance, Supplier<IGeneratable> oreGeneratable, Supplier<IGeneratable> netherOreGeneratable) {
		this.oreHardness = oreHardness;
		this.oreResistance = oreResistance;
		this.netherOreHardness = netherOreHardness;
		this.netherOreResistance = netherOreResistance;
		this.blockHardness = blockHardness;
		this.blockResistance = blockResistance;
		this.oreGeneratable = oreGeneratable;
		this.netherOreGeneratable = netherOreGeneratable;
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
	public Supplier<Float> getNetherOreHardness() {
		return () -> netherOreHardness.get();
	}
	
	@Override
	public Supplier<Float> getNetherOreResistance() {
		return () -> netherOreResistance.get();
	}
	
	@Override
	public Supplier<Float> getBlockHardness() {
		return () -> blockHardness.get();
	}
	
	@Override
	public Supplier<Float> getBlockResistance() {
		return () -> blockResistance.get();
	}
	
	@Override
	public Supplier<IGeneratable> getOreGeneratable() {
		return oreGeneratable;
	}
	
	@Override
	public Supplier<IGeneratable> getNetherOreGeneratable() {
		return netherOreGeneratable;
	}
	
}
