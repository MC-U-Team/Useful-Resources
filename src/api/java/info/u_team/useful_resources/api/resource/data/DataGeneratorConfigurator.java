package info.u_team.useful_resources.api.resource.data;

import java.util.Map;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.api.worldgen.WorldGenFeature;
import net.minecraft.world.storage.loot.LootTable;

public class DataGeneratorConfigurator implements IDataGeneratorConfigurator {
	
	private final ResourceType type;
	
	private final Map<String, Supplier<WorldGenFeature>> worldGenFeatures;
	private final Map<BlockResourceType, Supplier<LootTable>> extraLootTables;
	private final Map<String, Object> extraProperties;
	
	public DataGeneratorConfigurator(ResourceType type, Map<String, Supplier<WorldGenFeature>> worldGenFeatures, Map<BlockResourceType, Supplier<LootTable>> extraLootTables, Map<String, Object> extraProperties) {
		this.type = type;
		this.worldGenFeatures = worldGenFeatures;
		this.extraLootTables = extraLootTables;
		this.extraProperties = extraProperties;
	}
	
	@Override
	public ResourceType getResourceType() {
		return type;
	}
	
	@Override
	public Map<String, Supplier<WorldGenFeature>> getWorldGeneration() {
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