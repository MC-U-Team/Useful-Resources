package info.u_team.useful_resources.api.resource.data;

import java.util.Map;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.world.storage.loot.LootTable;

public interface IDataGeneratorConfigurator {
	
	Map<String, WorldGenFeature> getWorldGeneration();
	
	Map<BlockResourceType, LootTable> getExtraLootTables();
	
	Map<String, Object> getExtraProperties();
	
}
