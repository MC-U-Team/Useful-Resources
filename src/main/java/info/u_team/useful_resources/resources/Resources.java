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
import info.u_team.useful_resources.resource.ObsidianResource;
import info.u_team.useful_resources.util.LootTableUtil;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;

public class Resources {
	
	// VANILLA
	
	public static final IResource IRON = new VanillaIngotResource("iron", 0xf5f5f5, 1, 3, ORE) //
			.setExisting(ORE, Blocks.IRON_ORE) //
			.setExisting(BLOCK, Blocks.IRON_BLOCK) //
			.setExisting(BARS, Blocks.IRON_BARS) //
			.setExisting(CHAIN, Blocks.CHAIN) //
			.setExisting(DOOR, Blocks.IRON_DOOR) //
			.setExisting(TRAPDOOR, Blocks.IRON_TRAPDOOR) //
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
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 10, 0, 0, 64));
	
	public static final IResource GOLD = new VanillaIngotResource("gold", 0xFFE60A, 2, 3, ORE, NETHER_ORE) //
			.setExisting(ORE, Blocks.GOLD_ORE) //
			.setExisting(NETHER_ORE, Blocks.NETHER_GOLD_ORE) //
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
			.setBars() //
			.setChain() //
			.setDoor() //
			.setTrapDoor() //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 6, 5, 0, 0, 48));
	
	public static final IResource DIAMOND = new VanillaGemResource("diamond", 0x65FCF2, 2, 3, true, 3, 7, ORE) //
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
			.setLootTableWithFortune(END_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 8, 1, 0, 0, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 8, 1, 0, 0, 64));
	
	public static final IResource EMERALD = new VanillaGemResource("emerald", 0x0ccf4a, 2, 3, false, 3, 7, ORE) //
			.setExisting(ORE, Blocks.EMERALD_ORE) //
			.setExisting(BLOCK, Blocks.EMERALD_BLOCK) //
			.setExisting(GEM, Items.EMERALD) //
			.setArmor(createArmor(25, new int[] { 3, 6, 8, 3 }, 11)) //
			.setHorseArmor(11) //
			.setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 3, 1000, 8, 3, 10)) //
			.setLootTableWithFortune(NETHER_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setLootTableWithFortune(END_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 1) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 6, 2, 0, 0, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 6, 2, 0, 0, 64));
	
	public static final IResource LAPIS = new VanillaGemResource("lapis", 0x526BDE, 1, 3, true, 2, 5, ORE) //
			.setExisting(ORE, Blocks.LAPIS_ORE) //
			.setExisting(BLOCK, Blocks.LAPIS_BLOCK) //
			.setExisting(GEM, Items.LAPIS_LAZULI) //
			.setArmor(createArmor(20, new int[] { 3, 5, 7, 3 }, 20)) //
			.setHorseArmor(9) //
			.setTools(createTools(new float[] { 4.5F, 0, 1, 1.5F, 2.5F }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 2, 500, 7, 3, 20)) //
			.setLootTableWithFortune(NETHER_ORE, GEM, (ore, drop) -> createFortuneBlockLootTableWithCount(ore, drop, 4, 9)) //
			.setLootTableWithFortune(END_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 0.2F) //
			.setProperty("nether_oreModelOverride", "lapis_netherrack_nether_ore") //
			.setProperty("end_oreModelOverride", "lapis_endstone_end_ore") //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureDepthAverageNether(state, 7, 1, 64, 64)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureDepthAverageEndIslands(state, 7, 2, 35, 20));
	
	public static final IResource QUARTZ = new VanillaGemResource("quartz", 0xF7F7E4, 1, 3, true, 2, 5, NETHER_ORE) //
			.setExisting(NETHER_ORE, Blocks.NETHER_QUARTZ_ORE) //
			.setExisting(BLOCK, Blocks.QUARTZ_BLOCK) //
			.setExisting(GEM, Items.QUARTZ) //
			.setArmor(createArmor(20, new int[] { 2, 5, 7, 2 }, 15)) //
			.setHorseArmor(8) //
			.setTools(createTools(new float[] { 5, 0, 1, 1.5F, 3.5F }, new float[] { -2.8F, 0, -2.5F, -3, -2.1F }, 1, 400, 7, 2, 15)) //
			.setLootTableWithFortune(ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setLootTableWithFortune(END_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 0.2F) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 6, 4, 0, 0, 64)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 10, 1, 0, 0, 64));
	
	public static final IResource COAL = new VanillaGemResource("coal", 0x3B3834, 0, 3, true, 0, 2, ORE) //
			.setExisting(ORE, Blocks.COAL_ORE) //
			.setExisting(BLOCK, Blocks.COAL_BLOCK) //
			.setExisting(GEM, Items.COAL) //
			.setLootTableWithFortune(NETHER_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setLootTableWithFortune(END_ORE, GEM, LootTableUtil::createFortuneBlockLootTable) //
			.setProperty("oreSmeltingXp", 0.1F) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 17, 20, 0, 0, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 15, 8, 0, 0, 64));
	
	public static final IResource REDSTONE = new VanillaDustResource("redstone", 0xA31C29, 2, 3, 1, 5, ORE) //
			.setExisting(ORE, Blocks.REDSTONE_ORE) //
			.setExisting(BLOCK, Blocks.REDSTONE_BLOCK) //
			.setExisting(DUST, Items.REDSTONE) //
			.setLootTableWithFortune(NETHER_ORE, DUST, (ore, drop) -> createFortuneBlockLootTableWithCount(ore, drop, 4, 5)) //
			.setLootTableWithFortune(END_ORE, DUST, (ore, drop) -> createFortuneBlockLootTableWithCount(ore, drop, 4, 5)) //
			.setProperty("oreSmeltingXp", 0.7F) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 8, 8, 0, 0, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 8, 4, 0, 0, 32));
	
	public static final IResource OBSIDIAN = new ObsidianResource("obsidian", 0x2E293A) //
			.setExisting(BLOCK, Blocks.OBSIDIAN);
	
	// INGOT
	
	public static final IResource COPPER = new BasicIngotResource("copper", 0xFF8000, Rarity.COMMON, 1, 3) //
			.setArmor(createArmor(12, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(6) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 200, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 15, 0, 0, 64));
	
	public static final IResource TIN = new BasicIngotResource("tin", 0xEDEDED, Rarity.COMMON, 1, 3) //
			.setArmor(createArmor(12, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(6) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 200, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 15, 0, 0, 64));
	
	public static final IResource ALUMINUM = new BasicIngotResource("aluminum", 0x80C8F5, Rarity.COMMON, 1, 2.6F) //
			.setArmor(createArmor(10, new int[] { 1, 3, 5, 2 }, 9)) //
			.setHorseArmor(5) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 1, 150, 5, 1, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 15, 0, 0, 64));
	
	public static final IResource SILVER = new BasicIngotResource("silver", 0xDEDEFF, Rarity.COMMON, 2, 4) //
			.setArmor(createArmor(15, new int[] { 2, 5, 6, 2 }, 9)) //
			.setHorseArmor(7) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 250, 6, 2, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 2, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 3, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 4, 0, 0, 64));
	
	public static final IResource LEAD = new BasicIngotResource("lead", 0x966B96, Rarity.COMMON, 2, 3) //
			.setArmor(createArmor(20, new int[] { 1, 3, 5, 2 }, 9)) //
			.setHorseArmor(7) //
			.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 1, 250, 6, 2, 14)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 10, 3, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 3, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 10, 4, 0, 0, 64));
	
	public static final IResource PLATINUM = new BasicIngotResource("platinum", 0xFFFFA6, Rarity.RARE, 2, 3) //
			.setArmor(createArmor(40, new int[] { 3, 6, 8, 3 }, 11)) //
			.setHorseArmor(11) //
			.setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 3, 2000, 8, 3, 10)) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 14, 2, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 14, 1, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 14, 2, 0, 0, 64));
	
	public static final IResource IRIDIUM = new BasicIngotResource("iridium", 0xFFFFFF, Rarity.UNCOMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 5, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 6, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 5, 0, 0, 64));
	
	public static final IResource NICKEL = new BasicIngotResource("nickel", 0xB8B8FF, Rarity.COMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 15, 0, 0, 64));
	
	public static final IResource ZINC = new BasicIngotResource("zinc", 0xF2EBEB, Rarity.COMMON, 1, 3) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 9, 20, 0, 0, 64)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 9, 15, 0, 0, 64));
	
	public static final IResource URANIUM_238 = new BasicRadioActiveIngotResource("uranium_238", 0x26A326, Rarity.UNCOMMON, 2, 4) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 14, 8, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 14, 20, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 14, 5, 0, 0, 64));
	
	public static final IResource URANIUM_235 = new BasicRadioActiveIngotResource("uranium_235", 0x0ee60e, Rarity.EPIC, 2, 5) //
			.setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 3, 1, 0, 0, 32)) //
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 3, 2, 10, 20, 128)) //
			.setGenerationDefault(END_ORE, state -> createOreFeatureRangeEndIslands(state, 3, 1, 0, 0, 64));
	
	// GEM
	
	public static final IResource RUBY = new CommonGemResource("ruby", 0xC41A1A);
	
	public static final IResource SAPPHIRE = new CommonGemResource("sapphire", 0x1023B0);
	
	public static final IResource MALACHITE = new CommonGemResource("malachite", 0x258F7F);
	
	public static final IResource PERIDOT = new CommonGemResource("peridot", 0x4F8C2B);
	
	public static final IResource TANZANITE = new CommonGemResource("tanzanite", 0x5421C2);
	
	// ALLOY
	
	public static final BasicAlloyResource BRASS = new BasicAlloyResource("brass", 0xE3AC22, Rarity.COMMON, 2, 3, 4) //
			.addIngredient(of(() -> COPPER, INGOT, 3)) //
			.addIngredient(of(() -> ZINC, INGOT, 1));
	
	public static final BasicAlloyResource BRONZE = new BasicAlloyResource("bronze", 0xFF943D, Rarity.COMMON, 2, 3, 4) //
			.addIngredient(of(() -> COPPER, INGOT, 3)) //
			.addIngredient(of(() -> TIN, INGOT, 1));
	
	public static final BasicAlloyResource STEEL = new BasicAlloyResource("steel", 0x878787, Rarity.COMMON, 2, 5, 1) //
			.addIngredient(of(() -> IRON, INGOT, 1)) //
			.addIngredient(of(() -> COAL, DUST, 4));
	
	public static final BasicAlloyResource DARK_STEEL = new BasicAlloyResource("dark_steel", 0x363636, Rarity.COMMON, 2, 6, 1) //
			.addIngredient(of(() -> STEEL, INGOT, 1)) //
			.addIngredient(of(() -> Blocks.OBSIDIAN, 1));
	
	public static final BasicAlloyResource ELECTRUM = new BasicAlloyResource("electrum", 0xFBFF82, Rarity.COMMON, 2, 3, 2) //
			.addIngredient(of(() -> GOLD, INGOT, 1)) //
			.addIngredient(of(() -> SILVER, INGOT, 1));
	
	public static final BasicAlloyResource INVAR = new BasicAlloyResource("invar", 0xbec9b3, Rarity.COMMON, 2, 3, 3) //
			.addIngredient(of(() -> NICKEL, INGOT, 1)) //
			.addIngredient(of(() -> IRON, INGOT, 2));
	
	public static final BasicAlloyResource CONSTANTAN = new BasicAlloyResource("constantan", 0xe0bd89, Rarity.COMMON, 2, 3, 2) //
			.addIngredient(of(() -> NICKEL, INGOT, 1)) //
			.addIngredient(of(() -> COPPER, INGOT, 1));
	
	public static final BasicAlloyResource SIGNALUM = new BasicAlloyResource("signalum", 0xE05316, Rarity.UNCOMMON, 2, 3, 4) //
			.addIngredient(of(() -> COPPER, INGOT, 3)) //
			.addIngredient(of(() -> SILVER, INGOT, 1)) //
			.addIngredient(of(() -> REDSTONE, DUST, 10));
	
	public static final BasicAlloyResource LUMIUM = new BasicAlloyResource("lumium", 0xFFE97A, Rarity.UNCOMMON, 2, 3, 4) //
			.addIngredient(of(() -> TIN, INGOT, 3)) //
			.addIngredient(of(() -> SILVER, INGOT, 1)) //
			.addIngredient(of(() -> Items.GLOWSTONE_DUST, 4));
	
	public static final BasicAlloyResource ENDERIUM = new BasicAlloyResource("enderium", 0x0d696e, Rarity.RARE, 2, 3, 4) //
			.addIngredient(of(() -> LEAD, INGOT, 3)) //
			.addIngredient(of(() -> PLATINUM, INGOT, 1)) //
			.addIngredient(of(() -> Items.ENDER_PEARL, 4));
	
	// // TODO REMOVE
	// public static final IResource DEBUG = new BasicIngotResource("debug", 0, Rarity.EPIC, 1, 3) {
	//
	// @Override
	// public int getColor() {
	// return 0xA31C29;
	// };
	// };
	
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
		ResourceRegistry.register(OBSIDIAN);
		
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
		ResourceRegistry.register(URANIUM_238);
		ResourceRegistry.register(URANIUM_235);
		
		// GEM
		ResourceRegistry.register(RUBY);
		ResourceRegistry.register(SAPPHIRE);
		ResourceRegistry.register(MALACHITE);
		ResourceRegistry.register(PERIDOT);
		ResourceRegistry.register(TANZANITE);
		
		// ALLOY
		ResourceRegistry.register(BRASS);
		ResourceRegistry.register(BRONZE);
		ResourceRegistry.register(STEEL);
		ResourceRegistry.register(DARK_STEEL);
		ResourceRegistry.register(ELECTRUM);
		ResourceRegistry.register(INVAR);
		ResourceRegistry.register(CONSTANTAN);
		ResourceRegistry.register(SIGNALUM);
		ResourceRegistry.register(LUMIUM);
		ResourceRegistry.register(ENDERIUM);
		
		// ResourceRegistry.register(DEBUG); // TODO REMOVE
	}
	
}
