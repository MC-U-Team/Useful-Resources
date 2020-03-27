package info.u_team.useful_resources.util;

import info.u_team.useful_resources.data.provider.ResourceLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.storage.loot.LootTable;

public class LootTableUtil {
	
	public static LootTable createFortuneBlockLootTable(Block block, IItemProvider item) {
		return ResourceLootTableProvider.addFortuneBlockLootTable(block, item);
	}
	
}
