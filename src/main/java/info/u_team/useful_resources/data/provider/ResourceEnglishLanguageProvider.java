package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.UsefulResourcesMod.MODID;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.*;

import com.google.gson.JsonObject;

import info.u_team.u_team_core.data.CommonProvider;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.*;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.Item;

public class ResourceEnglishLanguageProvider extends CommonProvider {
	
	public ResourceEnglishLanguageProvider(DataGenerator generator) {
		super("Resources-English-Language", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		JsonObject object = new JsonObject();
		
		object.addProperty("itemGroup." + MODID + ".group", "Useful Resources");
		
		Resources.VALUES.forEach(resource -> {
			
			final IResourceBlocks blocks = resource.getBlocks();
			final IResourceItems items = resource.getItems();
			
			for (Block block : blocks.getArray()) {
				final String name = block.getRegistryName().getPath();
				if (block == blocks.getBlock(ResourceBlockTypes.BLOCK)) {
					object.addProperty("block." + MODID + "." + name, "Block of " + capitalize(resource.getName()));
				} else {
					object.addProperty("block." + MODID + "." + name, capitalize(name.replace("_", " ")));
				}
			}
			for (Item item : items.getArray()) {
				final String name = item.getRegistryName().getPath();
				object.addProperty("item." + MODID + "." + name, capitalize(name.replace("_", " ")));
			}
		});
		try {
			write(cache, object, path);
		} catch (IOException ex) {
			LOGGER.error(marker, "Could not write data.", ex);
		}
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveAssets(outputFolder, MODID).resolve("lang").resolve("en_us.json");
	}
	
	public String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
