package info.u_team.useful_resources.api;

import java.util.function.Supplier;

public interface IResourceConfig {
	
	Supplier<Float> getOreHardness();
	
	Supplier<Float> getOreResistance();
	
	Supplier<Float> getNetherOreHardness();
	
	Supplier<Float> getNetherOreResistance();
	
	Supplier<Float> getBlockHardness();
	
	Supplier<Float> getBlockResistance();
	
	Supplier<IGeneratable> getOreGeneratable();
	
	Supplier<IGeneratable> getNetherOreGeneratable();
	
}