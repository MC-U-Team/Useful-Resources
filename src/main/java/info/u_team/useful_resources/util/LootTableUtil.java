package info.u_team.useful_resources.util;

import info.u_team.useful_resources.data.provider.ResourceLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.util.IItemProvider;

public class LootTableUtil {
	
	public static void createFortuneBlockLootTable(Block block, IItemProvider item) {
		ResourceLootTableProvider.addFortuneBlockLootTable(block, item);
	}
	
}
