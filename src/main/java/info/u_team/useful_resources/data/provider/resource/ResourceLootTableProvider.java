package info.u_team.useful_resources.data.provider.resource;

import java.util.function.BiConsumer;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

public class ResourceLootTableProvider extends CommonLootTablesProvider {
	
	public ResourceLootTableProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		ResourceRegistry.getResources().stream().flatMap(resource -> resource.getBlocks().values().stream()).forEach(block -> {
			registerBlock(block, addBasicBlockLootTable(block), consumer);
		});
	}
}
