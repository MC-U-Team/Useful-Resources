package info.u_team.useful_resources.resources;

import static info.u_team.useful_resources.api.stack.ItemCountProvider.of;
import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static info.u_team.useful_resources.util.GenerationUtil.*;
import static info.u_team.useful_resources.util.LootTableUtil.createFortuneBlockLootTableWithCount;
import static info.u_team.useful_resources.util.MaterialUtil.*;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.special.*;
import info.u_team.useful_resources.util.LootTableUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;

public class Resources {
	
	// VANILLA
	
	public static final IResource IRON = new VanillaIngotResource("iron", 0xf5f5f5, 1, 3, 3) //
			.setExisting(ORE, Blocks.IRON_ORE) //
			.setExisting(BLOCK, Blocks.IRON_BLOCK) //
			.setExisting(INGOT, Items.IRON_INGOT) //
			.setExisting(NUGGET, Items.IRON_NUGGET) //
			.setExisting(HELMET, Items.IRON_HELMET) //
			.setExisting(CHESTPLATE, Items.IRON_CHESTPLATE) //
			.setExisting(LEGGINGS, Items.IRON_LEGGINGS) //
			.setExisting(BOOTS, Items.IRON_BOOTS) //
			.setExisting(HORSE_ARMOR, Items.IRON_HORSE_ARMOR) //
			.setExisting(AXE, Items.IRON_AXE) //
			.setExisting(HOE, Items.IRON_HOE) //
			.setExisting(PICKAXE, Items.IRON_PICKAXE) //
			.setExisting(SHOVEL, Items.IRON_SHOVEL) //
			.setExisting(SWORD, Items.IRON_SWORD) //
			.setProperty("oreSmeltingXp", 0.7F) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource GOLD = new VanillaIngotResource("gold", 0xfff373, 2, 3, 3) //
			.setExisting(ORE, Blocks.GOLD_ORE) //
			.setExisting(BLOCK, Blocks.GOLD_BLOCK) //
			.setExisting(INGOT, Items.GOLD_INGOT) //
			.setExisting(NUGGET, Items.GOLD_NUGGET) //
			.setExisting(HELMET, Items.GOLDEN_HELMET) //
			.setExisting(CHESTPLATE, Items.GOLDEN_CHESTPLATE) //
			.setExisting(LEGGINGS, Items.GOLDEN_LEGGINGS) //
			.setExisting(BOOTS, Items.GOLDEN_BOOTS) //
			.setExisting(HORSE_ARMOR, Items.GOLDEN_HORSE_ARMOR) //
			.setExisting(AXE, Items.GOLDEN_AXE) //
			.setExisting(HOE, Items.GOLDEN_HOE) //
			.setExisting(PICKAXE, Items.GOLDEN_PICKAXE) //
			.setExisting(SHOVEL, Items.GOLDEN_SHOVEL) //
			.setExisting(SWORD, Items.GOLDEN_SWORD) //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource DIAMOND = new VanillaGemResource("diamond", 0x1cb5e8, 2, 3, 3, NETHER_ORE, true, 3, 7) //
			.setExisting(ORE, Blocks.DIAMOND_ORE) //
			.setExisting(BLOCK, Blocks.DIAMOND_BLOCK) //
			.setExisting(GEM, Items.DIAMOND) //
			.setExisting(HELMET, Items.DIAMOND_HELMET) //
			.setExisting(CHESTPLATE, Items.DIAMOND_CHESTPLATE) //
			.setExisting(LEGGINGS, Items.DIAMOND_LEGGINGS) //
			.setExisting(BOOTS, Items.DIAMOND_BOOTS) //
			.setExisting(HORSE_ARMOR, Items.DIAMOND_HORSE_ARMOR) //
			.setExisting(AXE, Items.DIAMOND_AXE) //
			.setExisting(HOE, Items.DIAMOND_HOE) //
			.setExisting(PICKAXE, Items.DIAMOND_PICKAXE) //
			.setExisting(SHOVEL, Items.DIAMOND_SHOVEL) //
			.setExisting(SWORD, Items.DIAMOND_SWORD) //
			.setLootTableWithFortune(NETHER_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 8, 1, 0, 0, 128));
	
	public static final IResource EMERALD = new VanillaGemResource("emerald", 0x0ccf4a, 2, 3, 3, NETHER_ORE, false, 3, 7) //
			.setExisting(ORE, Blocks.EMERALD_ORE) //
			.setExisting(BLOCK, Blocks.EMERALD_BLOCK) //
			.setExisting(GEM, Items.EMERALD) //
			.setArmor(createArmor(25, new int[] { 3, 6, 8, 3 }, 11)) //
			.setHorseArmor(11) //
			.setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 3, 1000, 8, 3, 10)) //
			.setLootTableWithFortune(NETHER_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 6, 2, 0, 0, 128));
	
	public static final IResource LAPIS = new VanillaGemResource("lapis", 0x1037e6, 1, 3, 3, NETHER_ORE, true, 2, 5) //
			.setExisting(ORE, Blocks.LAPIS_ORE) //
			.setExisting(BLOCK, Blocks.LAPIS_BLOCK) //
			.setExisting(GEM, Items.LAPIS_LAZULI) //
			.setArmor(createArmor(20, new int[] { 3, 5, 7, 3 }, 20)) //
			.setHorseArmor(9) //
			.setTools(createTools(new float[] { 4.5F, 0, 1, 1.5F, 2.5F }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 2, 500, 7, 3, 20)) //
			.setLootTableWithFortune(NETHER_ORE, GEM, (ore, drop) -> createFortuneBlockLootTableWithCount(ore, drop, 4, 9)) //
			.setProperty("oreSmeltingXp", 0.2F) //
			.setProperty("nether_oreModelOverride", "lapis_netherrack_nether_ore") //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureDeathAverageNether(state, 7, 1, 64, 64));
	
	public static final IResource QUARTZ = new VanillaGemResource("quartz", 0xfcfcf7, 1, 3, 3, ORE, true, 2, 5) //
			.setExisting(NETHER_ORE, Blocks.NETHER_QUARTZ_ORE) //
			.setExisting(BLOCK, Blocks.QUARTZ_BLOCK) //
			.setExisting(GEM, Items.QUARTZ) //
			.setArmor(createArmor(20, new int[] { 2, 5, 7, 2 }, 15)) //
			.setHorseArmor(8) //
			.setTools(createTools(new float[] { 5, 0, 1, 1.5F, 3.5F }, new float[] { -2.8F, 0, -2.5F, -3, -2.1F }, 1, 400, 7, 2, 15)) //
			.setLootTableWithFortune(ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 0.2F) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 6, 4, 0, 0, 64));
	
	public static final IResource COAL = new VanillaGemResource("coal", 0x121110, 0, 3, 3, NETHER_ORE, true, 0, 2) //
			.setExisting(ORE, Blocks.COAL_ORE) //
			.setExisting(BLOCK, Blocks.COAL_BLOCK) //
			.setExisting(GEM, Items.COAL) //
			.setLootTableWithFortune(ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 0.1F) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 17, 20, 0, 0, 128));
	
	public static final IResource REDSTONE = new VanillaDustResource("redstone", 0xc41b2c, 2, 3, 3, 1, 5) //
			.setExisting(ORE, Blocks.REDSTONE_ORE) //
			.setExisting(DUST, Items.REDSTONE) //
			.setLootTableWithFortune(NETHER_ORE, DUST, (ore, drop) -> createFortuneBlockLootTableWithCount(ore, drop, 4, 5)) //
			.setProperty("oreSmeltingXp", 0.7F) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 8, 8, 0, 0, 128));
	
	// INGOT
	
	public static final IResource COPPER = new BasicIngotResource("copper", 0xc46c29, Rarity.COMMON, 1, 3) //
			.setArmor(createArmor(12, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(6) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 200, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource TIN = new BasicIngotResource("tin", 0xbad3db, Rarity.COMMON, 1, 3) //
			.setArmor(createArmor(12, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(6) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 200, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource ALUMINUM = new BasicIngotResource("aluminum", 0xebf0ee, Rarity.COMMON, 1, 2.6F) //
			.setArmor(createArmor(10, new int[] { 1, 3, 5, 2 }, 9)) //
			.setHorseArmor(5) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 1, 150, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource SILVER = new BasicIngotResource("silver", 0xabe9ff, Rarity.COMMON, 2, 4) //
			.setArmor(createArmor(15, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(7) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 250, 6, 2, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 2, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 3, 10, 20, 128));
	
	public static final IResource LEAD = new BasicIngotResource("lead", 0x5b62c9, Rarity.COMMON, 2, 3) //
			.setArmor(createArmor(20, new int[] { 1, 3, 5, 2 }, 9)) //
			.setHorseArmor(7) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 1, 250, 6, 2, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 10, 3, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 3, 10, 20, 128));
	
	public static final IResource PLATINUM = new BasicIngotResource("platinum", 0x6bb0ed, Rarity.RARE, 2, 3) //
			.setArmor(createArmor(40, new int[] { 3, 6, 8, 3 }, 11)) //
			.setHorseArmor(11) //
			.setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 3, 2000, 8, 3, 10)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 14, 2, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 14, 1, 10, 20, 128));
	
	public static final IResource IRIDIUM = new BasicIngotResource("iridium", 0x90e5e8, Rarity.COMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 5, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 6, 10, 20, 128));
	
	public static final IResource NICKEL = new BasicIngotResource("nickel", 0xcadbd4, Rarity.COMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource ZINC = new BasicIngotResource("zinc", 0x8ca7bd, Rarity.COMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource URANIUM = new BasicIngotResource("uranium", 0x17b350, Rarity.UNCOMMON, 2, 4) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 14, 8, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 14, 20, 10, 20, 128));
	
	// GEM
	
	public static final IResource RUBY = new CommonGemResource("ruby", 0xc41a1a);
	
	public static final IResource SAPPHIRE = new CommonGemResource("sapphire", 0x1023b0);
	
	// ALLOY
	
	public static final BasicAlloyResource BRASS = new BasicAlloyResource("brass", 0xe3ac22, Rarity.COMMON, 2, 3, 4) //
			.addIngredient(of(() -> COPPER, INGOT, 3)) //
			.addIngredient(of(() -> ZINC, INGOT, 1));
	
	public static final BasicAlloyResource BRONZE = new BasicAlloyResource("bronze", 0xb56b16, Rarity.COMMON, 2, 3, 4) //
			.addIngredient(of(() -> COPPER, INGOT, 3)) //
			.addIngredient(of(() -> TIN, INGOT, 1));
	
	public static final BasicAlloyResource STEEL = new BasicAlloyResource("steel", 0xbfbfbf, Rarity.COMMON, 2, 5, 1) //
			.addIngredient(of(() -> IRON, INGOT, 1)) //
			.addIngredient(of(() -> COAL, DUST, 4));
	
	public static final BasicAlloyResource DARK_STEEL = new BasicAlloyResource("dark_steel", 0x616161, Rarity.COMMON, 2, 6, 1) //
			.addIngredient(of(() -> STEEL, INGOT, 1)) //
			.addIngredient(of(() -> Blocks.OBSIDIAN, 1));
	
	public static void register() {
		// Vanilla
		ResourceRegistry.register(IRON);
		ResourceRegistry.register(GOLD);
		ResourceRegistry.register(DIAMOND);
		ResourceRegistry.register(EMERALD);
		ResourceRegistry.register(LAPIS);
		ResourceRegistry.register(QUARTZ);
		ResourceRegistry.register(COAL);
		ResourceRegistry.register(REDSTONE);
		
		// INGOT
		ResourceRegistry.register(COPPER);
		ResourceRegistry.register(TIN);
		ResourceRegistry.register(ALUMINUM);
		ResourceRegistry.register(SILVER);
		ResourceRegistry.register(LEAD);
		ResourceRegistry.register(PLATINUM);
		ResourceRegistry.register(IRIDIUM);
		ResourceRegistry.register(NICKEL);
		ResourceRegistry.register(ZINC);
		ResourceRegistry.register(URANIUM);
		
		// GEM
		ResourceRegistry.register(RUBY);
		ResourceRegistry.register(SAPPHIRE);
		
		// ALLOY
		ResourceRegistry.register(BRASS);
		ResourceRegistry.register(BRONZE);
		ResourceRegistry.register(STEEL);
		ResourceRegistry.register(DARK_STEEL);
	}
	
}
