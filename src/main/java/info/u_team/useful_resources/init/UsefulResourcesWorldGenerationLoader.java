package info.u_team.useful_resources.init;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;

import org.apache.logging.log4j.*;

import com.google.gson.*;
import com.mojang.serialization.JsonOps;

import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.worldgen.WorldGenFeatures;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

public class UsefulResourcesWorldGenerationLoader {
	
	private static final Logger LOGGER = LogManager.getLogger("Useful-Resources World Generation");
	
	private static final Path WORLDGENERATION_PATH = FMLPaths.CONFIGDIR.get().resolve("usefulresources").resolve("worldgeneration").toAbsolutePath();
	private static final Path MARKER_PATH = WORLDGENERATION_PATH.resolve("world_generation_marker");
	
	private static final Gson GSON = new GsonBuilder().create();
	
	private static final String ACCEPTED_FILE_ENDING = ".json";
	
	private static final Map<String, WorldGenFeatures> FEATURES = new HashMap<>();
	
	private static void setup(FMLCommonSetupEvent event) {
		LamdbaExceptionUtils.uncheck(UsefulResourcesWorldGenerationLoader::setupWorldGenerationFolder);
		LamdbaExceptionUtils.uncheck(UsefulResourcesWorldGenerationLoader::loadWorldGenerationFolder);
		registerWorldGenerationDefinitions();
	}
	
	private static void setupWorldGenerationFolder() throws IOException {
		Files.createDirectories(WORLDGENERATION_PATH);
		
		if (!Files.exists(MARKER_PATH)) {
			extractWorldGenerationFiles();
		}
	}
	
	private static void extractWorldGenerationFiles() throws IOException {
		final Path rootPath = ModList.get().getModFileById(UsefulResourcesMod.MODID).getFile().findResource("world_generation").toAbsolutePath();
		
		LOGGER.info("Extract default world generation definition files from {} to {}", rootPath, WORLDGENERATION_PATH);
		
		try (final Stream<Path> stream = Files.walk(rootPath)) {
			stream.filter(Files::isRegularFile).forEach(LamdbaExceptionUtils.rethrowConsumer(internalPath -> {
				final Path exportedPath = WORLDGENERATION_PATH.resolve(rootPath.relativize(internalPath));
				Files.createDirectories(exportedPath.getParent());
				Files.copy(internalPath, exportedPath, StandardCopyOption.REPLACE_EXISTING);
			}));
		}
	}
	
	private static void loadWorldGenerationFolder() throws IOException {
		final List<Path> paths;
		
		try (final Stream<Path> stream = Files.walk(WORLDGENERATION_PATH)) {
			paths = stream.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(ACCEPTED_FILE_ENDING)).collect(Collectors.toList());
		}
		
		LOGGER.info("Try to load {} world generation definitions.", paths.size());
		
		paths.forEach(path -> {
			try (final BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
				final JsonElement json = JSONUtils.fromJson(GSON, reader, JsonElement.class);
				
				final WorldGenFeatures features = JsonOps.INSTANCE.withDecoder(WorldGenFeatures.CODEC).apply(json).resultOrPartial(error -> {
					throw new IllegalStateException("WorldGenFeatures: " + error);
				}).get().getFirst();
				
				final String id = WORLDGENERATION_PATH.relativize(path).toString().replace(".json", "").replace('\\', '/').replaceAll("[^a-z0-9/._-]", "");
				
				FEATURES.put(id, features);
			} catch (IOException | IllegalStateException | NoSuchElementException ex) {
				LOGGER.error("Could not read json file {} definition for world generation.", path, ex);
			}
		});
		
		LOGGER.info("Loaded {} world generation definitions.", paths.size());
	}
	
	private static void registerWorldGenerationDefinitions() {
		FEATURES.forEach((path, worldGenFeatures) -> {
			
			final List<List<Supplier<ConfiguredFeature<?, ?>>>> featuresDecoration = worldGenFeatures.getFeatures();
			for (int decorationStageIndex = 0; decorationStageIndex < featuresDecoration.size(); decorationStageIndex++) {
				final List<Supplier<ConfiguredFeature<?, ?>>> features = featuresDecoration.get(decorationStageIndex);
				for (int index = 0; index < features.size(); index++) {
					
					System.out.println("REGSITER FEATURE: " + path + "." + decorationStageIndex + "." + index);
					
					final ResourceLocation registryName = new ResourceLocation(UsefulResourcesMod.MODID, path + "." + decorationStageIndex + "." + index);
					
					System.out.println("Registry name: " + registryName);
					
					final ConfiguredFeature<?, ?> registeredFeature = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, registryName, features.get(index).get());
					
					// Replace the dummy loaded element with a registered one and always return this one from now on
					features.set(index, () -> registeredFeature);
					
					if (features.get(index).get() != registeredFeature) {
						System.out.println("___________ ERRORR _ _SD FJLÖSDHFLÖ JHSDHFJ");
					}
				}
			}
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesWorldGenerationLoader::setup);
	}
	
}
