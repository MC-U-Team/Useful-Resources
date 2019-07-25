package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.UsefulResourcesMod.MODID;

import java.io.IOException;
import java.nio.file.Path;

import com.google.gson.JsonObject;

import info.u_team.useful_resources.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.*;

public class ResourceItemModelsProvider extends CommonProvider {
	
	public ResourceItemModelsProvider(DataGenerator generator) {
		super("Resources-Item-Models", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		Resources.VALUES.forEach(resource -> {
			for (Block block : resource.getBlocks().getArray()) {
				final String blockName = block.getRegistryName().getPath();
				
				JsonObject object = new JsonObject();
				object.addProperty("parent", MODID + ":block/" + blockName);
				
				try {
					write(cache, object, path.resolve(blockName + ".json"));
				} catch (IOException ex) {
					LOGGER.error(marker, "Could not write data.", ex);
				}
			}
			
			for (Item item : resource.getItems().getArray()) {
				final String itemName = item.getRegistryName().getPath();
				final boolean isTiered = item instanceof TieredItem;
				
				JsonObject object = new JsonObject();
				JsonObject texturesObject = new JsonObject();
				
				if (isTiered) {
					object.addProperty("parent", "item/handheld");
				} else {
					object.addProperty("parent", "item/generated");
				}
				texturesObject.addProperty("layer0", MODID + ":item/" + resource.getName() + "/" + itemName.replace(resource.getName() + "_", ""));
				object.add("textures", texturesObject);
				
				try {
					write(cache, object, path.resolve(itemName + ".json"));
				} catch (IOException ex) {
					LOGGER.error(marker, "Could not write data.", ex);
				}
			}
		});
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveAssets(outputFolder).resolve("models").resolve("item");
	}
}
