package info.u_team.useful_resources.init;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

public class UsefulResourcesWorldGenerationLoader {
	
	private static final Path WORLDGENERATION_PATH = FMLPaths.CONFIGDIR.get().resolve("usefulresources").resolve("worldgeneration");
	private static final Path MARKER_PATH = WORLDGENERATION_PATH.resolve("world_generation_marker");
	
	private static void setup(FMLCommonSetupEvent event) {
		LamdbaExceptionUtils.uncheck(UsefulResourcesWorldGenerationLoader::setupWorldGenerationFolder);
	}
	
	private static void setupWorldGenerationFolder() throws IOException {
		Files.createDirectories(WORLDGENERATION_PATH);
		
		if (!Files.exists(MARKER_PATH)) {
			extractWorldGenerationFiles();
		}
	}
	
	private static void extractWorldGenerationFiles() throws IOException {
		final Path rootPath = ModList.get().getModFileById(UsefulResourcesMod.MODID).getFile().findResource("world_generation").toAbsolutePath();
		
		try (final Stream<Path> stream = Files.walk(rootPath)) {
			stream.filter(Files::isRegularFile).forEach(LamdbaExceptionUtils.rethrowConsumer(internalPath -> {
				final Path exportedPath = WORLDGENERATION_PATH.resolve(rootPath.relativize(internalPath));
				Files.createDirectories(exportedPath.getParent());
				Files.copy(internalPath, exportedPath, StandardCopyOption.REPLACE_EXISTING);
			}));
		}
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesWorldGenerationLoader::setup);
	}
	
}
