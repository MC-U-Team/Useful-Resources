package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static net.minecraft.data.CookingRecipeBuilder.*;
import static net.minecraft.data.ShapedRecipeBuilder.shapedRecipe;
import static net.minecraft.data.ShapelessRecipeBuilder.shapelessRecipe;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ResourceRecipesProvider extends CommonRecipesProvider {
	
	public ResourceRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ResourceRegistry.getResources().forEach(resource -> {
			final Map<BlockResourceType, Block> blocks = resource.getBlocks();
			final Map<ItemResourceType, Item> items = resource.getItems();
			
			// ORE -> INGOT
			if (shouldAddRecipe(resource, ORE, INGOT)) {
				final Tag<Item> oreTag = getItemTag(ORE, resource);
				final Item ingotItem = items.get(INGOT);
				
				smeltingRecipe(getIngredientOfTag(oreTag), ingotItem, 0.7F, 200) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "smelting/ingot_from_ore"));
				
				blastingRecipe(getIngredientOfTag(oreTag), ingotItem, 0.7F, 100) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "blasting/ingot_from_ore"));
			}
			
			// NETHER_ORE -> INGOT
			if (shouldAddRecipe(resource, NETHER_ORE, INGOT)) {
				final Tag<Item> oreTag = getItemTag(NETHER_ORE, resource);
				final Item ingotItem = items.get(INGOT);
				
				smeltingRecipe(getIngredientOfTag(oreTag), ingotItem, 0.7F, 200) //
						.addCriterion("has_nether_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "smelting/ingot_from_nether_ore"));
				
				blastingRecipe(getIngredientOfTag(oreTag), ingotItem, 0.7F, 100) //
						.addCriterion("has_nether_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "blasting/ingot_from_nether_ore"));
			}
			
			// DUST -> INGOT
			if (shouldAddRecipe(resource, DUST, INGOT)) {
				final Tag<Item> dustTag = DUST.getTag(resource);
				final Item ingotItem = items.get(INGOT);
				
				smeltingRecipe(getIngredientOfTag(dustTag), ingotItem, 0.5F, 200) //
						.addCriterion("has_dust", hasItem(dustTag)) //
						.build(consumer, createLocation(resource, "smelting/ingot_from_dust"));
				
				blastingRecipe(getIngredientOfTag(dustTag), ingotItem, 0.5F, 100) //
						.addCriterion("has_dust", hasItem(dustTag)) //
						.build(consumer, createLocation(resource, "blasting/ingot_from_dust"));
			}
			
			// INGOT <-> BLOCK
			if (shouldAddRecipe(resource, INGOT, BLOCK)) {
				// INGOT -> BLOCK
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Block blockBlock = blocks.get(BLOCK);
				shapedRecipe(blockBlock) //
						.key('#', ingotTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/block_from_ingot"));
				
				// BLOCK -> INGOT
				final Tag<Item> blockTag = getItemTag(BLOCK, resource);
				final Item ingotItem = items.get(INGOT);
				
				shapelessRecipe(ingotItem, 9) //
						.addIngredient(blockTag) //
						.addCriterion("has_block", hasItem(blockTag)) //
						.build(consumer, createLocation(resource, "crafting/ingot_from_block"));
			}
			
			// NUGGET <-> INGOT
			if (shouldAddRecipe(resource, NUGGET, INGOT)) {
				// NUGGET -> INGOT
				final Tag<Item> nuggetTag = NUGGET.getTag(resource);
				final Item ingotItem = items.get(INGOT);
				shapedRecipe(ingotItem) //
						.key('#', nuggetTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_nugget", hasItem(nuggetTag)) //
						.build(consumer, createLocation(resource, "crafting/ingot_from_nugget"));
				
				// INGOT -> NUGGET
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Item nuggetItem = items.get(NUGGET);
				
				shapelessRecipe(nuggetItem, 9) //
						.addIngredient(ingotTag) //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/nugget_from_ingot"));
			}
			
			// INGOT -> AXE
			if (shouldAddRecipe(resource, INGOT, AXE)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item axeItem = items.get(AXE);
				
				shapedRecipe(axeItem) //
						.key('#', ingotTag) //
						.key('X', stickTag) //
						.patternLine("##") //
						.patternLine("#X") //
						.patternLine(" X") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/axe_from_ingot"));
			}
			
			// INGOT -> HOE
			if (shouldAddRecipe(resource, INGOT, HOE)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item hoeItem = items.get(HOE);
				
				shapedRecipe(hoeItem) //
						.key('#', ingotTag) //
						.key('X', stickTag) //
						.patternLine("##") //
						.patternLine(" X") //
						.patternLine(" X") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/hoe_from_ingot"));
			}
			
			// INGOT -> PICKAXE
			if (shouldAddRecipe(resource, INGOT, PICKAXE)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item pickaxeItem = items.get(PICKAXE);
				
				shapedRecipe(pickaxeItem) //
						.key('#', ingotTag) //
						.key('X', stickTag) //
						.patternLine("###") //
						.patternLine(" X ") //
						.patternLine(" X ") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/pickaxe_from_ingot"));
			}
			
			// INGOT -> SHOVEL
			if (shouldAddRecipe(resource, INGOT, SHOVEL)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item shovelItem = items.get(SHOVEL);
				
				shapedRecipe(shovelItem) //
						.key('#', ingotTag) //
						.key('X', stickTag) //
						.patternLine("#") //
						.patternLine("X") //
						.patternLine("X") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/shovel_from_ingot"));
			}
			
			// INGOT -> SWORD
			if (shouldAddRecipe(resource, INGOT, SWORD)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item swordItem = items.get(SWORD);
				
				shapedRecipe(swordItem) //
						.key('#', ingotTag) //
						.key('X', stickTag) //
						.patternLine("#") //
						.patternLine("#") //
						.patternLine("X") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/sword_from_ingot"));
			}
			
			// INGOT -> HELMET
			if (shouldAddRecipe(resource, INGOT, HELMET)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Item helmetItem = items.get(HELMET);
				
				shapedRecipe(helmetItem) //
						.key('#', ingotTag) //
						.patternLine("###") //
						.patternLine("# #") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/helmet_from_ingot"));
			}
			
			// INGOT -> CHESTPLATE
			if (shouldAddRecipe(resource, INGOT, CHESTPLATE)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Item chestplateItem = items.get(CHESTPLATE);
				
				shapedRecipe(chestplateItem) //
						.key('#', ingotTag) //
						.patternLine("# #") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/chestplate_from_ingot"));
			}
			
			// INGOT -> LEGGINGS
			if (shouldAddRecipe(resource, INGOT, LEGGINGS)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Item leggingsItem = items.get(LEGGINGS);
				
				shapedRecipe(leggingsItem) //
						.key('#', ingotTag) //
						.patternLine("###") //
						.patternLine("# #") //
						.patternLine("# #") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/leggings_from_ingot"));
			}
			
			// INGOT -> BOOTS
			if (shouldAddRecipe(resource, INGOT, BOOTS)) {
				final Tag<Item> ingotTag = INGOT.getTag(resource);
				final Item bootsItem = items.get(BOOTS);
				
				shapedRecipe(bootsItem) //
						.key('#', ingotTag) //
						.patternLine("# #") //
						.patternLine("# #") //
						.addCriterion("has_ingot", hasItem(ingotTag)) //
						.build(consumer, createLocation(resource, "crafting/boots_from_ingot"));
			}
		});
	}
	
	private boolean shouldAddRecipe(IResource resource, IResourceType<?>... types) {
		final boolean allTypes = Stream.of(types).allMatch(type -> {
			if (type instanceof BlockResourceType) {
				return resource.getBlocks().containsKey(type);
			} else if (type instanceof FluidResourceType) {
				return resource.getFluids().containsKey(type);
			} else if (type instanceof ItemResourceType) {
				return resource.getItems().containsKey(type);
			}
			return false;
		});
		final boolean anyRegistered = Stream.of(types).anyMatch(type -> {
			if (type instanceof BlockResourceType) {
				return resource.getRegistryBlocks().contains(resource.getBlocks().get(type));
			} else if (type instanceof FluidResourceType) {
				return resource.getRegistryFluids().contains(resource.getFluids().get(type));
			} else if (type instanceof ItemResourceType) {
				return resource.getRegistryItems().contains(resource.getItems().get(type));
			}
			return false;
		});
		return allTypes && anyRegistered;
	}
	
	private final Tag<Item> getItemTag(BlockResourceType type, IResource resource) {
		return TagUtil.fromBlockTag(type.getTag(resource));
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(modid, resource.getName() + "/" + name);
	}
	
}
