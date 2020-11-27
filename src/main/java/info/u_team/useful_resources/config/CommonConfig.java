package info.u_team.useful_resources.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class CommonConfig {
	
	public static final ForgeConfigSpec CONFIG;
	private static final CommonConfig INSTANCE;
	
	static {
		final Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		CONFIG = pair.getRight();
		INSTANCE = pair.getLeft();
	}
	
	public static CommonConfig getInstance() {
		return INSTANCE;
	}
	
	public final BooleanValue worldGenerationEnabled;
	
	private CommonConfig(ForgeConfigSpec.Builder builder) {
		worldGenerationEnabled = builder.comment("If the world generation should be enabled.").define("worldGenerationEnabled", true);
	}
	
}