package info.u_team.useful_resources.util;

import info.u_team.useful_resources.data.provider.ResourceLootTableProvider;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.functions.*;

public class LootTableUtil {
	
	public static LootTable createFortuneBlockLootTable(Block block, IItemProvider item) {
		return ResourceLootTableProvider.addFortuneBlockLootTable(block, item);
	}
	
	public static LootTable createFortuneBlockLootTableWithCount(Block block, IItemProvider item, float min, float max) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.BLOCK) //
				.addLootPool(LootPool.builder() //
						.rolls(ConstantRange.of(1)) //
						.addEntry(ItemLootEntry.builder(block) //
								.acceptCondition(MatchTool.builder(ItemPredicate.Builder.create() //
										.enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))) //
								).alternatively(ItemLootEntry.builder(item) //
										.acceptFunction(SetCount.builder(RandomValueRange.of(min, max))) //
										.acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)) //
										.acceptFunction(ExplosionDecay.builder()))))
				.build();
	}
	
}
