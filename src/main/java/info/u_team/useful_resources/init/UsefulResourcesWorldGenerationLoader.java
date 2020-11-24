package info.u_team.useful_resources.init;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

import cpw.mods.modlauncher.api.LamdbaExceptionUtils;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

public class UsefulResourcesWorldGenerationLoader {
	
	private static final Path WORLDGENERATION_PATH = FMLPaths.CONFIGDIR.get().resolve("usefulresources/worldgeneration");
	
	private static void setup(FMLCommonSetupEvent event) {
		try {
			Files.createDirectories(WORLDGENERATION_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extractWorldGenerationFiles();
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
