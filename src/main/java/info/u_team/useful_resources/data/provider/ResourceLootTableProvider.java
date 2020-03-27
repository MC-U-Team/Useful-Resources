package info.u_team.useful_resources.data.provider;

import java.util.Map;
import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.BlockResourceType;
import net.minecraft.block.Block;
import net.minecraft.util.*;
import net.minecraft.world.storage.loot.*;

public class ResourceLootTableProvider extends CommonLootTablesProvider {
	
	public ResourceLootTableProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				final LootTable lootTable;
				final Map<BlockResourceType, LootTable> extraLootTables = resource.getDataGeneratorConfigurator().getExtraLootTables();
				if (block.getLootTable().equals(LootTables.EMPTY)) {
					lootTable = null;
				} else if (extraLootTables.containsKey(type)) {
					lootTable = extraLootTables.get(type);
				} else {
					lootTable = addBasicBlockLootTable(block);
				}
				if (lootTable != null) {
					registerBlock(block, lootTable, consumer);
				}
			});
		});
	}
	
	// Make this method public for the loot table util
	public static LootTable addFortuneBlockLootTable(Block block, IItemProvider item) {
		return CommonLootTablesProvider.addFortuneBlockLootTable(block, item);
	}
	
}
