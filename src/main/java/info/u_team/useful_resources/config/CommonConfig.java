package info.u_team.useful_resources.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

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
	
//	private final Gson gson = new GsonBuilder().registerTypeAdapter(GeneratableConfig.class, new GeneratableConfig.Serializer()).setPrettyPrinting().create();
	
//	public final BooleanValue worldGenerationEnabled;
	
	private CommonConfig(Builder builder) {
//		worldGenerationEnabled = builder.comment("If ores are generated. Every ore can be disabled in the json configs.").define("worldGenerationEnabled", true);
		
//		builder.comment("Resource config").push("resources");
		
//		Resources.VALUES.forEach(resource -> resource.setConfig(createResourceConfig(builder, resource)));
		
//		builder.pop();
	}
	
//	private ResourceConfig createResourceConfig(Builder builder, Resource resource) {
//		builder.push(resource.getName());
//		final DefaultConfig defaultConfig = resource.getDefaultConfig();
//		final ConfigValue<Float> oreHardness = builder.comment("Hardness of the ore block").defineInRange("oreHardness", defaultConfig.getOreHardness(), -1F, 1e5F, Float.class);
//		final ConfigValue<Float> oreResistance = builder.comment("Resistance of the ore block").defineInRange("oreResistance", defaultConfig.getOreResistance(), -1F, 1e6F, Float.class);
//		final ConfigValue<Float> netherOreHardness = builder.comment("Hardness of the nether ore block").defineInRange("netherOreHardness", defaultConfig.getNetherOreHardness(), -1F, 1e5F, Float.class);
//		final ConfigValue<Float> netherOreResistance = builder.comment("Resistance of the nether ore block").defineInRange("netherOreResistance", defaultConfig.getNetherOreResistance(), -1F, 1e6F, Float.class);
//		final ConfigValue<Float> blockHardness = builder.comment("Hardness of the resource block").defineInRange("blockHardness", defaultConfig.getBlockHardness(), -1F, 1e5F, Float.class);
//		final ConfigValue<Float> blockResistance = builder.comment("Resistance of the resource block").defineInRange("blockResistance", defaultConfig.getBlockResistance(), -1F, 1e6F, Float.class);
//		builder.pop();
//		
//		Pair<Supplier<IGeneratable>, Supplier<IGeneratable>> configPair = createGeneratableConfig(resource.getName(), defaultConfig.getOreConfig(), defaultConfig.getNetherOreConfig());
//		
//		return new ResourceConfig(oreHardness, oreResistance, netherOreHardness, netherOreResistance, blockHardness, blockResistance, configPair.getLeft(), configPair.getRight());
//	}
//	
//	private Pair<Supplier<IGeneratable>, Supplier<IGeneratable>> createGeneratableConfig(String name, GeneratableConfig defaultOreConfig, GeneratableConfig defaultNetherOreConfig) {
//		final Path configPath = FMLPaths.CONFIGDIR.get().resolve(UsefulResourcesMod.MODID).resolve("worldgen").resolve(name + ".json");
//		if (!Files.exists(configPath) || !Files.isRegularFile(configPath)) {
//			try {
//				Files.deleteIfExists(configPath);
//				Files.createDirectories(configPath.getParent());
//				Files.createFile(configPath);
//				try (BufferedWriter bufferedWriter = Files.newBufferedWriter(configPath)) {
//					GeneratableOres ores = new GeneratableOres(defaultOreConfig, defaultNetherOreConfig);
//					gson.toJson(ores, bufferedWriter);
//				}
//			} catch (Exception ex) {
//				throw new RuntimeException("Could not create config file for " + name, ex);
//			}
//		}
//		try (BufferedReader bufferedReader = Files.newBufferedReader(configPath)) {
//			GeneratableOres ores = gson.fromJson(bufferedReader, GeneratableOres.class);
//			return Pair.of(() -> ores.getOreGeneretable(), () -> ores.getNetherOreGeneretable());
//		} catch (IOException ex) {
//			throw new RuntimeException("Could not read config file for " + name, ex);
//		} catch (JsonParseException ex) {
//			throw new RuntimeException("Could not parse config file for " + name, ex);
//		}
//	}
//	
//	private static class GeneratableOres {
//		
//		@SerializedName(value = "ore")
//		private GeneratableConfig oreGeneretable;
//		@SerializedName(value = "nether_ore")
//		private GeneratableConfig netherOreGeneretable;
//		
//		private GeneratableOres(GeneratableConfig oreGeneretable, GeneratableConfig netherOreGeneretable) {
//			this.oreGeneretable = oreGeneretable;
//			this.netherOreGeneretable = netherOreGeneretable;
//		}
//		
//		public GeneratableConfig getOreGeneretable() {
//			return oreGeneretable;
//		}
//		
//		public GeneratableConfig getNetherOreGeneretable() {
//			return netherOreGeneretable;
//		}
//	}
}
