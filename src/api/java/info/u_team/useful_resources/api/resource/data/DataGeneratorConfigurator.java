package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.*;
import net.minecraft.loot.LootTable;

public class DataGeneratorConfigurator implements IDataGeneratorConfigurator {
	
	private final ResourceDataType type;
	
	private final Map<String, Supplier<IWorldGenFeatures>> worldGenFeatures;
	private final Map<BlockResourceType, Supplier<LootTable>> extraLootTables;
	private final Map<String, Object> extraProperties;
	
	public DataGeneratorConfigurator(ResourceDataType type, Map<String, Supplier<IWorldGenFeatures>> worldGenFeatures, Map<BlockResourceType, Supplier<LootTable>> extraLootTables, Map<String, Object> extraProperties) {
		this.type = type;
		this.worldGenFeatures = worldGenFeatures;
		this.extraLootTables = extraLootTables;
		this.extraProperties = extraProperties;
	}
	
	@Override
	public ResourceDataType getResourceType() {
		return type;
	}
	
	@Override
	public Map<String, Supplier<IWorldGenFeatures>> getWorldGeneration() {
		return worldGenFeatures;
	}
	
	@Override
	public Map<BlockResourceType, Supplier<LootTable>> getExtraLootTables() {
		return extraLootTables;
	}
	
	@Override
	public Map<String, Object> getExtraProperties() {
		return extraProperties;
	}
	
}
