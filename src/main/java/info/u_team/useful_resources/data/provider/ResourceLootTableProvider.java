package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.UsefulResourcesMod.MODID;

import java.io.IOException;
import java.nio.file.Path;

import com.google.gson.*;

import info.u_team.u_team_core.data.CommonProvider;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.data.*;

public class ResourceLootTableProvider extends CommonProvider {
	
	public ResourceLootTableProvider(DataGenerator generator) {
		super("Resources-Loot-Tables", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		Resources.VALUES.forEach(resource -> {
			for (Block block : resource.getBlocks().getArray()) {
				final String blockName = block.getRegistryName().getPath();
				
				JsonObject object = new JsonObject();
				JsonArray poolsArray = new JsonArray();
				JsonObject poolsObject = new JsonObject();
				JsonArray entriesArray = new JsonArray();
				JsonObject entriesObject = new JsonObject();
				JsonArray conditionsArray = new JsonArray();
				JsonObject conditionsObject = new JsonObject();
				
				object.addProperty("type", "minecraft:block");
				poolsObject.addProperty("rolls", 1);
				
				entriesObject.addProperty("type", "minecraft:item");
				entriesObject.addProperty("name", block.getRegistryName().toString());
				entriesArray.add(entriesObject);
				poolsObject.add("entries", entriesArray);
				
				conditionsObject.addProperty("condition", "minecraft:survives_explosion");
				conditionsArray.add(conditionsObject);
				poolsObject.add("conditions", conditionsArray);
				
				poolsArray.add(poolsObject);
				object.add("pools", poolsArray);
				
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
		return resolveData(outputFolder, MODID).resolve("loot_tables").resolve("blocks");
	}
}
