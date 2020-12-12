package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.IWorldGenFeatures;
import net.minecraft.loot.LootTable;

public interface IDataGeneratorConfigurator {
	
	IResourceDataType getResourceType();
	
	Map<String, Supplier<IWorldGenFeatures>> getWorldGeneration();
	
	Map<BlockResourceType, Supplier<LootTable>> getLootTables();
	
	Map<String, Object> getProperties();
	
	default void clearDataGeneratorConfig() {
		getWorldGeneration().clear();
		getLootTables().clear();
		getProperties().clear();
	}
	
}
