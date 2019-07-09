package info.u_team.useful_ores.api;

import java.util.function.Supplier;

public interface IResourceConfig {
	
	Supplier<Float> getOreHardness();
	
	Supplier<Float> getOreResistance();
	
	Supplier<Float> getNetherOreHardness();
	
	Supplier<Float> getNetherOreResistance();
	
	Supplier<Float> getBlockHardness();
	
	Supplier<Float> getBlockResistance();
	
}