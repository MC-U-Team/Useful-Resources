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
			
			final ItemResourceType normalResourceType = getNormalResourceType(resource);
			final ItemResourceType tinyResourceType = getTinyResourceType(resource);
			
			final String normalResourceTypeName = normalResourceType.getName();
			final String tinyResourceTypeName = tinyResourceType.getName();
			
			// ORE -> INGOT / GEM
			if (shouldAddRecipe(resource, ORE, normalResourceType)) {
				final Tag<Item> oreTag = getItemTag(ORE, resource);
				final Item normalItem = items.get(normalResourceType);
				
				smeltingRecipe(getIngredientOfTag(oreTag), normalItem, 0.7F, 200) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "smelting/" + normalResourceTypeName + "_from_ore"));
				
				blastingRecipe(getIngredientOfTag(oreTag), normalItem, 0.7F, 100) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "blasting/" + normalResourceTypeName + "_from_ore"));
			}
			
			// NETHER_ORE -> INGOT / GEM
			if (shouldAddRecipe(resource, NETHER_ORE, normalResourceType)) {
				final Tag<Item> oreTag = getItemTag(NETHER_ORE, resource);
				final Item normalItem = items.get(normalResourceType);
				
				smeltingRecipe(getIngredientOfTag(oreTag), normalItem, 0.7F, 200) //
						.addCriterion("has_nether_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "smelting/" + normalResourceTypeName + "_from_nether_ore"));
				
				blastingRecipe(getIngredientOfTag(oreTag), normalItem, 0.7F, 100) //
						.addCriterion("has_nether_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "blasting/" + normalResourceTypeName + "_from_nether_ore"));
			}
			
			// DUST -> INGOT / GEM
			if (shouldAddRecipe(resource, DUST, normalResourceType)) {
				final Tag<Item> dustTag = DUST.getTag(resource);
				final Item normalItem = items.get(normalResourceType);
				
				smeltingRecipe(getIngredientOfTag(dustTag), normalItem, 0.5F, 200) //
						.addCriterion("has_dust", hasItem(dustTag)) //
						.build(consumer, createLocation(resource, "smelting/" + normalResourceTypeName + "_from_dust"));
				
				blastingRecipe(getIngredientOfTag(dustTag), normalItem, 0.5F, 100) //
						.addCriterion("has_dust", hasItem(dustTag)) //
						.build(consumer, createLocation(resource, "blasting/" + normalResourceTypeName + "_from_dust"));
			}
			
			// INGOT / GEM <-> BLOCK
			if (shouldAddRecipe(resource, normalResourceType, BLOCK)) {
				// INGOT / GEM -> BLOCK
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Block blockBlock = blocks.get(BLOCK);
				shapedRecipe(blockBlock) //
						.key('#', normalTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/block_from_" + normalResourceTypeName));
				
				// BLOCK -> INGOT / GEM
				final Tag<Item> blockTag = getItemTag(BLOCK, resource);
				final Item normalItem = items.get(normalResourceType);
				
				shapelessRecipe(normalItem, 9) //
						.addIngredient(blockTag) //
						.addCriterion("has_block", hasItem(blockTag)) //
						.build(consumer, createLocation(resource, "crafting/" + normalResourceTypeName + "_from_block"));
			}
			
			// NUGGET / PIECE <-> INGOT / GEM
			if (shouldAddRecipe(resource, tinyResourceType, normalResourceType)) {
				// NUGGET / PIECE -> INGOT / GEM
				final Tag<Item> tinyTag = tinyResourceType.getTag(resource);
				final Item normalItem = items.get(normalResourceType);
				shapedRecipe(normalItem) //
						.key('#', tinyTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_" + tinyResourceTypeName, hasItem(tinyTag)) //
						.build(consumer, createLocation(resource, "crafting/" + normalResourceTypeName + "_from_" + tinyResourceTypeName));
				
				// INGOT / GEM -> NUGGET / PIECE
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Item tinyItem = items.get(tinyResourceType);
				
				shapelessRecipe(tinyItem, 9) //
						.addIngredient(normalTag) //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/" + tinyResourceTypeName + "_from_" + normalResourceTypeName));
			}
			
			// INGOT / GEM -> AXE
			if (shouldAddRecipe(resource, normalResourceType, AXE)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item axeItem = items.get(AXE);
				
				shapedRecipe(axeItem) //
						.key('#', normalTag) //
						.key('X', stickTag) //
						.patternLine("##") //
						.patternLine("#X") //
						.patternLine(" X") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/axe_from_" + normalResourceTypeName));
			}
			
			// INGOT / GEM -> HOE
			if (shouldAddRecipe(resource, normalResourceType, HOE)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item hoeItem = items.get(HOE);
				
				shapedRecipe(hoeItem) //
						.key('#', normalTag) //
						.key('X', stickTag) //
						.patternLine("##") //
						.patternLine(" X") //
						.patternLine(" X") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/hoe_from_" + normalResourceTypeName));
			}
			
			// INGOT / GEM -> PICKAXE
			if (shouldAddRecipe(resource, normalResourceType, PICKAXE)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item pickaxeItem = items.get(PICKAXE);
				
				shapedRecipe(pickaxeItem) //
						.key('#', normalTag) //
						.key('X', stickTag) //
						.patternLine("###") //
						.patternLine(" X ") //
						.patternLine(" X ") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/pickaxe_from_" + normalResourceTypeName));
			}
			
			// INGOT / GEM -> SHOVEL
			if (shouldAddRecipe(resource, normalResourceType, SHOVEL)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item shovelItem = items.get(SHOVEL);
				
				shapedRecipe(shovelItem) //
						.key('#', normalTag) //
						.key('X', stickTag) //
						.patternLine("#") //
						.patternLine("X") //
						.patternLine("X") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/shovel_from_" + normalResourceTypeName));
			}
			
			// INGOT -> SWORD
			if (shouldAddRecipe(resource, normalResourceType, SWORD)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Tag<Item> stickTag = Tags.Items.RODS_WOODEN;
				final Item swordItem = items.get(SWORD);
				
				shapedRecipe(swordItem) //
						.key('#', normalTag) //
						.key('X', stickTag) //
						.patternLine("#") //
						.patternLine("#") //
						.patternLine("X") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/sword_from_" + normalResourceTypeName));
			}
			
			// INGOT -> HELMET
			if (shouldAddRecipe(resource, normalResourceType, HELMET)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Item helmetItem = items.get(HELMET);
				
				shapedRecipe(helmetItem) //
						.key('#', normalTag) //
						.patternLine("###") //
						.patternLine("# #") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/helmet_from_" + normalResourceTypeName));
			}
			
			// INGOT -> CHESTPLATE
			if (shouldAddRecipe(resource, normalResourceType, CHESTPLATE)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Item chestplateItem = items.get(CHESTPLATE);
				
				shapedRecipe(chestplateItem) //
						.key('#', normalTag) //
						.patternLine("# #") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/chestplate_from_" + normalResourceTypeName));
			}
			
			// INGOT -> LEGGINGS
			if (shouldAddRecipe(resource, normalResourceType, LEGGINGS)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Item leggingsItem = items.get(LEGGINGS);
				
				shapedRecipe(leggingsItem) //
						.key('#', normalTag) //
						.patternLine("###") //
						.patternLine("# #") //
						.patternLine("# #") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/leggings_from_" + normalResourceTypeName));
			}
			
			// INGOT -> BOOTS
			if (shouldAddRecipe(resource, normalResourceType, BOOTS)) {
				final Tag<Item> normalTag = normalResourceType.getTag(resource);
				final Item bootsItem = items.get(BOOTS);
				
				shapedRecipe(bootsItem) //
						.key('#', normalTag) //
						.patternLine("# #") //
						.patternLine("# #") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/boots_from_" + normalResourceTypeName));
			}
		});
	}
	
	private ItemResourceType getNormalResourceType(IResource resource) {
		if (resource.getItems().containsKey(INGOT)) {
			return INGOT;
		}
		return GEM;
	}
	
	private ItemResourceType getTinyResourceType(IResource resource) {
		if (resource.getItems().containsKey(NUGGET)) {
			return NUGGET;
		}
		return PIECE;
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
