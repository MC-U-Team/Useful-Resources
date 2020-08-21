package info.u_team.useful_resources.data.provider;

import java.util.Map;
import java.util.function.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.BlockResourceType;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;

public class ResourceLootTableProvider extends CommonLootTablesProvider {
	
	public ResourceLootTableProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				final LootTable lootTable;
				final Map<BlockResourceType, Supplier<LootTable>> extraLootTables = resource.getDataGeneratorConfigurator().getExtraLootTables();
				if (block.getLootTable().equals(LootTables.EMPTY)) {
					lootTable = null;
				} else if (extraLootTables.containsKey(type)) {
					lootTable = extraLootTables.get(type).get();
				} else {
					lootTable = addBasicBlockLootTable(block);
				}
				if (lootTable != null) {
					registerBlock(block, lootTable, consumer);
				}
			});
		});
	}
}
