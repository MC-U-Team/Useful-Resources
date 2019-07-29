package info.u_team.useful_resources.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;

public class CommonConfig {
	
	public static final ForgeConfigSpec CONFIG;
	private static final CommonConfig INSTANCE;
	
	static {
		Pair<CommonConfig, ForgeConfigSpec> pair = new Builder().configure(CommonConfig::new);
		CONFIG = pair.getRight();
		INSTANCE = pair.getLeft();
	}
	
	public static CommonConfig getInstance() {
		return INSTANCE;
	}
	
	public final BooleanValue worldGenerationEnabled;
	
	private CommonConfig(Builder builder) {
		worldGenerationEnabled = builder.comment("If the world generation should be enabled. Every ore can be disabled in the json configs.").define("worldGenerationEnabled", true);
	}
	
}
