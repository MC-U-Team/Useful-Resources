package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.WorldGenFeatures;
import net.minecraft.loot.LootTable;

public interface IDataGeneratorConfigurator {
	
	ResourceType getResourceType();
	
	Map<String, Supplier<WorldGenFeatures>> getWorldGeneration();
	
	Map<BlockResourceType, Supplier<LootTable>> getExtraLootTables();
	
	Map<String, Object> getExtraProperties();
	
	default void clearDataGeneratorConfig() {
		getWorldGeneration().clear();
		getExtraLootTables().clear();
		getExtraProperties().clear();
	}
	
}
