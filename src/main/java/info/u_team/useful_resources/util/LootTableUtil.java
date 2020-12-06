package info.u_team.useful_resources.util;

import net.minecraft.advancements.criterion.*;
import net.minecraft.block.*;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.*;
import net.minecraft.loot.functions.*;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.IItemProvider;

public class LootTableUtil {
	
	public static LootTable createFortuneBlockLootTable(IItemProvider block, IItemProvider item) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.BLOCK) //
				.addLootPool(LootPool.builder() //
						.rolls(ConstantRange.of(1)) //
						.addEntry(ItemLootEntry.builder(block) //
								.acceptCondition(MatchTool.builder(ItemPredicate.Builder.create() //
										.enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))) //
								).alternatively(ItemLootEntry.builder(item) //
										.acceptFunction(ApplyBonus.oreDrops(Enchantments.FORTUNE)) //
										.acceptFunction(ExplosionDecay.builder()))))
				.build();
	}
	
	public static LootTable createFortuneBlockLootTableWithCount(IItemProvider block, IItemProvider item, float min, float max) {
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
	
	public static LootTable createDoorBlockLootTable(IItemProvider item, Block block) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.BLOCK) //
				.addLootPool(LootPool.builder() //
						.rolls(ConstantRange.of(1)) //
						.addEntry(ItemLootEntry.builder(item) //
								.acceptCondition(BlockStateProperty.builder(block) //
										.fromProperties(StatePropertiesPredicate.Builder.newBuilder() //
												.withProp(DoorBlock.HALF, DoubleBlockHalf.LOWER))) //
						).acceptCondition(SurvivesExplosion.builder())) //
				.build();
	}
}
