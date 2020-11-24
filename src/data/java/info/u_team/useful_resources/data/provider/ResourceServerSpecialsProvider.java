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
		} catch (final IOException ex) {
			LOGGER.error(marker, "Could not write data.", ex);
		}
	}
	
	@Override
	public String getName() {
		return "Server-Special-Provider";
	}
	
}
