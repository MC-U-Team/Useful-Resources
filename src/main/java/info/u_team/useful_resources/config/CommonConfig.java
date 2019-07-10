package info.u_team.useful_resources.config;

import java.io.*;
import java.nio.file.*;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.IGeneratable;
import info.u_team.useful_resources.api.IGeneratable.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.*;
import net.minecraftforge.fml.loading.FMLPaths;

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
	
	private final Gson gson = new GsonBuilder().registerTypeAdapter(GeneratableConfig.class, new GeneratableConfig.Serializer()).setPrettyPrinting().create();
	
	public final BooleanValue worldGenerationEnabled;
	
	public final ResourceConfig copper;
	
	private CommonConfig(Builder builder) {
		worldGenerationEnabled = builder.comment("If ores are generated. Every ore can be disabled in the json configs.").define("worldGenerationEnabled", true);
		
		builder.comment("Resource config").push("resources");
		copper = createResourceConfig(builder, "copper", 3F, 3F, 3F, 3F, 3F, 6F, new GeneratableConfig(true, ListType.BLACKLIST, new Category[] { Category.NETHER, Category.THEEND }, ListType.BLACKLIST, new Biome[] {}, 9, GenerationConfig.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64), null), new GeneratableConfig(true, ListType.WHITELIST, new Category[] { Category.NETHER }, ListType.BLACKLIST, new Biome[] {}, 9, GenerationConfig.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128), null));
		builder.pop();
	}
	
	private ResourceConfig createResourceConfig(Builder builder, String name, float defaultOreHardness, float defaultOreResistance, float defaultNetherOreHardness, float defaultNetherOreResistance, float defaultBlockHardness, float defaultBlockResistance, GeneratableConfig defaultOreConfig, GeneratableConfig defaultNetherOreConfig) {
		builder.push(name);
		final ConfigValue<Float> oreHardness = builder.comment("Hardness of the ore block").defineInRange("oreHardness", defaultOreHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> oreResistance = builder.comment("Resistance of the ore block").defineInRange("oreResistance", defaultOreResistance, -1F, 1e6F, Float.class);
		final ConfigValue<Float> netherOreHardness = builder.comment("Hardness of the nether ore block").defineInRange("netherOreHardness", defaultNetherOreHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> netherOreResistance = builder.comment("Resistance of the nether ore block").defineInRange("netherOreResistance", defaultNetherOreResistance, -1F, 1e6F, Float.class);
		final ConfigValue<Float> blockHardness = builder.comment("Hardness of the resource block").defineInRange("blockHardness", defaultBlockHardness, -1F, 1e5F, Float.class);
		final ConfigValue<Float> blockResistance = builder.comment("Resistance of the resource block").defineInRange("blockResistance", defaultBlockResistance, -1F, 1e6F, Float.class);
		builder.pop();
		
		Pair<Supplier<IGeneratable>, Supplier<IGeneratable>> configPair = createGeneratableConfig(name, defaultOreConfig, defaultNetherOreConfig);
		
		return new ResourceConfig(oreHardness, oreResistance, netherOreHardness, netherOreResistance, blockHardness, blockResistance, configPair.getLeft(), configPair.getRight());
	}
	
	private Pair<Supplier<IGeneratable>, Supplier<IGeneratable>> createGeneratableConfig(String name, GeneratableConfig defaultOreConfig, GeneratableConfig defaultNetherOreConfig) {
		final Path configPath = FMLPaths.CONFIGDIR.get().resolve(UsefulResourcesMod.MODID).resolve("worldgen").resolve(name + ".json");
		if (!Files.exists(configPath) || !Files.isRegularFile(configPath)) {
			try {
				Files.deleteIfExists(configPath);
				Files.createDirectories(configPath.getParent());
				Files.createFile(configPath);
				try (BufferedWriter bufferedWriter = Files.newBufferedWriter(configPath)) {
					GeneratableOres ores = new GeneratableOres(defaultOreConfig, defaultNetherOreConfig);
					gson.toJson(ores, bufferedWriter);
				}
			} catch (Exception ex) {
				throw new RuntimeException("Could not create config file for " + name, ex);
			}
		}
		try (BufferedReader bufferedReader = Files.newBufferedReader(configPath)) {
			GeneratableOres ores = gson.fromJson(bufferedReader, GeneratableOres.class);
			return Pair.of(() -> ores.getOreGeneretable(), () -> ores.getNetherOreGeneretable());
		} catch (IOException ex) {
			throw new RuntimeException("Could not read config file for " + name, ex);
		} catch (JsonParseException ex) {
			throw new RuntimeException("Could not parse config file for " + name, ex);
		}
	}
	
	private static class GeneratableOres {
		
		@SerializedName(value = "ore")
		private GeneratableConfig oreGeneretable;
		@SerializedName(value = "nether_ore")
		private GeneratableConfig netherOreGeneretable;
		
		private GeneratableOres(GeneratableConfig oreGeneretable, GeneratableConfig netherOreGeneretable) {
			this.oreGeneretable = oreGeneretable;
			this.netherOreGeneretable = netherOreGeneretable;
		}
		
		public GeneratableConfig getOreGeneretable() {
			return oreGeneretable;
		}
		
		public GeneratableConfig getNetherOreGeneretable() {
			return netherOreGeneretable;
		}
	}
}
