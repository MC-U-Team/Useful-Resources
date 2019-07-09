package info.u_team.useful_ores.config;

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
	
	public final ResourceConfig copper;
	
	private CommonConfig(Builder builder) {
		builder.comment("Resource config").push("resources");
		copper = createResourceConfig(builder, "copper", 3F, 3F, 3F, 3F, 3F, 6F);
		builder.pop();
	}
	
	private ResourceConfig createResourceConfig(Builder builder, String name, float defaultOreHardness, float defaultOreResistance, float defaultNetherOreHardness, float defaultNetherOreResistance, float defaultBlockHardness, float defaultBlockResistance) {
		builder.push(name);
		final ConfigValue<Float> oreHardness = builder.comment("Hardness of the ore block").defineInRange("oreHardness", defaultOreHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> oreResistance = builder.comment("Resistance of the ore block").defineInRange("oreResistance", defaultOreResistance, -1F, 1e6F, Float.class);
		final ConfigValue<Float> netherOreHardness = builder.comment("Hardness of the nether ore block").defineInRange("netherOreHardness", defaultNetherOreHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> netherOreResistance = builder.comment("Resistance of the nether ore block").defineInRange("netherOreResistance", defaultNetherOreResistance, -1F, 1e6F, Float.class);
		final ConfigValue<Float> blockHardness = builder.comment("Hardness of the resource block").defineInRange("blockHardness", defaultBlockHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> blockResistance = builder.comment("Resistance of the resource block").defineInRange("blockResistance", defaultBlockResistance, -1F, 1e6F, Float.class);
		builder.pop();
		return new ResourceConfig(oreHardness, oreResistance, netherOreHardness, netherOreResistance, blockHardness, blockResistance);
	}
	
}
