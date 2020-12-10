package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.util.LootTableUtil.createDoorBlockLootTable;

import java.util.Map;
import java.util.function.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.data.resource.GenerationResources;
import info.u_team.useful_resources.data.util.LootTableGenerationDecider;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

public class ResourceLootTablesProvider extends CommonLootTablesProvider {
	
	public ResourceLootTablesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		// Define special loot tables
		final LootTableGenerationDecider lootTableDecider = LootTableGenerationDecider.create();
		
		lootTableDecider.addSpecial(BlockResourceType.DOOR, (resource, type, block, configurator) -> {
			return createDoorBlockLootTable(block, block);
		});
		
		// Generate loot tables
		GenerationResources.forEachBlock((resource, type, block) -> {
			IDataGeneratorConfigurator configurator = resource.getDataGeneratorConfigurator();
			final Map<BlockResourceType, Supplier<LootTable>> extraLootTables = configurator.getExtraLootTables();
			
			final LootTable lootTable;
			if (block.getLootTable().equals(LootTables.EMPTY)) {
				lootTable = null;
			} else if (extraLootTables.containsKey(type)) {
				lootTable = extraLootTables.get(type).get();
			} else {
				lootTable = lootTableDecider.decide(resource, type, block, configurator, entry -> {
					return addBasicBlockLootTable(entry);
				});
			}
			
			if (lootTable != null) {
				registerBlock(block, lootTable, consumer);
			}
		});
	}
}
