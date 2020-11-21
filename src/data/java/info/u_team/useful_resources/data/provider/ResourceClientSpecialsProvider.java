package info.u_team.useful_resources.data.provider;

import java.io.IOException;

import com.google.gson.JsonObject;

import info.u_team.u_team_core.data.*;
import net.minecraft.data.DirectoryCache;

public class ResourceClientSpecialsProvider extends CommonProvider {
	
	public ResourceClientSpecialsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		write(cache, new JsonObject(), resolveModAssets().resolve("particles").resolve("colored_overlay_block.json"));
	}
	
	@Override
	public String getName() {
		return "Client-Special-Provider";
	}
	
}
