package info.u_team.useful_resources.resources;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static info.u_team.useful_resources.util.GenerationUtil.*;
import static info.u_team.useful_resources.util.MaterialUtil.*;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.special.*;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;

public class Resources {
	
	// VANILLA
	
	public static final IResource IRON = new VanillaIngotResource("iron", 0xf5f5f5, 1, 3) //
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
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	public static final IResource GOLD = new VanillaIngotResource("gold", 0xe0c812, 1, 3) //
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
			.setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 9, 10, 10, 20, 128));
	
	// CUSTOM
	
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
	
	public static void register() {
		ResourceRegistry.register(IRON);
		ResourceRegistry.register(GOLD);
		ResourceRegistry.register(COPPER);
		ResourceRegistry.register(TIN);
		ResourceRegistry.register(ALUMINUM);
		ResourceRegistry.register(SILVER);
		ResourceRegistry.register(LEAD);
	}
	
}
