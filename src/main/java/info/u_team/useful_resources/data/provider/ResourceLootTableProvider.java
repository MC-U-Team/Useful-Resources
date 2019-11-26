package info.u_team.useful_resources.data.provider;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;

public class ResourceLootTableProvider extends CommonLootTablesProvider {
	
	public ResourceLootTableProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		Resources.getValues().stream().flatMap(resource -> Stream.of(resource.getBlocks().getArray())).forEach(block -> {
			registerBlock(block, addBasicBlockLootTable(block), consumer);
		});
	}
}
