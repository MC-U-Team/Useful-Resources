package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static net.minecraft.data.CookingRecipeBuilder.*;
import static net.minecraft.data.ShapedRecipeBuilder.shapedRecipe;
import static net.minecraft.data.ShapelessRecipeBuilder.shapelessRecipe;

import java.util.Map;
import java.util.function.Consumer;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

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
			if (blocks.containsKey(ORE) && items.containsKey(INGOT)) {
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
			if (blocks.containsKey(NETHER_ORE) && items.containsKey(INGOT)) {
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
			if (items.containsKey(DUST) && items.containsKey(INGOT)) {
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
			if (items.containsKey(INGOT) && blocks.containsKey(BLOCK)) {
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
			if (items.containsKey(NUGGET) && items.containsKey(INGOT)) {
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
		});
	}
	
	private final Tag<Item> getItemTag(BlockResourceType type, IResource resource) {
		return TagUtil.fromBlockTag(type.getTag(resource));
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(modid, resource.getName() + "/" + name);
	}
	
	protected InventoryChangeTrigger.Instance hasItem(Tag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).build());
	}
	
}
