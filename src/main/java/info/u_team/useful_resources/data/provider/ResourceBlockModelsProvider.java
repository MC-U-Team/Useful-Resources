package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.UsefulResourcesMod.MODID;

import java.io.IOException;
import java.nio.file.Path;

import com.google.gson.JsonObject;

import info.u_team.u_team_core.data.CommonProvider;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.data.*;

public class ResourceBlockModelsProvider extends CommonProvider {
	
	public ResourceBlockModelsProvider(DataGenerator generator) {
		super("Resources-Block-Models", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		Resources.VALUES.forEach(resource -> {
			for (Block block : resource.getBlocks().getArray()) {
				final String blockName = block.getRegistryName().getPath();
				
				JsonObject object = new JsonObject();
				JsonObject texturesObject = new JsonObject();
				
				object.addProperty("parent", "block/cube_all");
				texturesObject.addProperty("all", MODID + ":block/" + resource.getName() + "/" + blockName.replace(resource.getName() + "_", ""));
				object.add("textures", texturesObject);
				
				try {
					write(cache, object, path.resolve(blockName + ".json"));
				} catch (IOException ex) {
					LOGGER.error(marker, "Could not write data.", ex);
				}
			}
		});
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveAssets(outputFolder, MODID).resolve("models").resolve("block");
	}
}
