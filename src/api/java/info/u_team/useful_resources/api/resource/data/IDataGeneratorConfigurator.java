package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.IWorldGenFeatures;
import net.minecraft.loot.LootTable;

public interface IDataGeneratorConfigurator {
	
	IResourceDataType getResourceType();
	
	Map<String, Supplier<IWorldGenFeatures>> getWorldGeneration();
	
	Map<BlockResourceType, Supplier<LootTable>> getExtraLootTables();
	
	Map<String, Object> getExtraProperties();
	
	default void clearDataGeneratorConfig() {
		getWorldGeneration().clear();
		getExtraLootTables().clear();
		getExtraProperties().clear();
	}
	
}
