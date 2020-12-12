package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.loot.LootTable;

public class DataGeneratorConfigurator implements IDataGeneratorConfigurator {
	
	private final ResourceDataType type;
	
	private final Map<String, Supplier<IWorldGenFeatures>> worldGeneration;
	private final Map<BlockResourceType, Supplier<LootTable>> lootTables;
	private final Map<String, Object> properties;
	
	public DataGeneratorConfigurator(ResourceDataType type, Map<String, Supplier<IWorldGenFeatures>> worldGenFeatures, Map<BlockResourceType, Supplier<LootTable>> lootTables, Map<String, Object> properties) {
		this.type = type;
		this.worldGeneration = worldGenFeatures;
		this.lootTables = lootTables;
		this.properties = properties;
	}
	
	@Override
	public ResourceDataType getResourceType() {
		return type;
	}
	
	@Override
	public Map<String, Supplier<IWorldGenFeatures>> getWorldGeneration() {
		return worldGeneration;
	}
	
	@Override
	public Map<BlockResourceType, Supplier<LootTable>> getLootTables() {
		return lootTables;
	}
	
	@Override
	public Map<String, Object> getProperties() {
		return properties;
	}
	
}
