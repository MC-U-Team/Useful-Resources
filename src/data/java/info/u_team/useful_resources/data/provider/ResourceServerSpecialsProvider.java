package info.u_team.useful_resources.data.provider;

import java.io.IOException;

import info.u_team.u_team_core.data.*;
import net.minecraft.data.DirectoryCache;

public class ResourceServerSpecialsProvider extends CommonProvider {
	
	public ResourceServerSpecialsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		try {
			write(cache, "", path.resolve("world_generation").resolve("world_generation_marker"));
			
			final String readmeText = "In this folder you can add or remove custom world generation definitions.\n" //
					+ "The generation files are in json. A definition can contain multiplate configured features\n" //
					+ "for each generation stage. Configured features are explained in the minecraft wiki.\n" //
					+ "Each file targets a specific set of biomes. If a feature can generate is determined with\n" //
					+ "a category and a biome white/black list. If both conditions are true the feature is added.\n" //
					+ "\n" //
					+ "To get the updated definitions (aften an update) please delete the world_generation_marker file.\n" //
					+ "This will extract all the default custom world generation definitions and will override any\n" //
					+ "changes to these files. New files will not be deleted. If you want to have a completely clean\n" //
					+ "installation please delete the parent folder (worldgeneration) of this file and rerun the game\n";
			
			write(cache, readmeText, path.resolve("world_generation").resolve("README.txt"));
		} catch (final IOException ex) {
			LOGGER.error(marker, "Could not write data.", ex);
		}
	}
	
	@Override
	public String getName() {
		return "Server-Special-Provider";
	}
	
}
