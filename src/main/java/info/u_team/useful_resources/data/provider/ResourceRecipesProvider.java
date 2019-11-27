package info.u_team.useful_resources.data.provider;

import java.util.function.Consumer;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.advancements.criterion.*;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
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
		/*Resources.getValues().forEach(resource -> {
			final String name = resource.getName();
			final IResourceItems items = resource.getItems();
			final IResourceBlocks blocks = resource.getBlocks();
			
			CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(items.getTag(DUST)), items.getItem(INGOT), 0.5F, 100).addCriterion("has_" + name + "_dust", hasItem(items.getTag(DUST))).build(consumer, createLocation(resource, "blasting/ingot_from_dust"));
			CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(blocks.getTag(ORE)), items.getItem(INGOT), 0.7F, 100).addCriterion("has_" + name + "_ore", hasItem(blocks.getTag(ORE))).build(consumer, createLocation(resource, "blasting/ingot_from_ore"));
			CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(blocks.getTag(NETHER_ORE)), blocks.getBlock(ORE), 1F, 100).addCriterion("has_" + name + "_nether_ore", hasItem(blocks.getTag(NETHER_ORE))).build(consumer, createLocation(resource, "blasting/ore_from_nether_ore"));
			
			CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(items.getTag(DUST)), items.getItem(INGOT), 0.5F, 200).addCriterion("has_" + name + "_dust", hasItem(items.getTag(DUST))).build(consumer, createLocation(resource, "smelting/ingot_from_dust"));
			CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(blocks.getTag(ORE)), items.getItem(INGOT), 0.7F, 200).addCriterion("has_" + name + "_ore", hasItem(blocks.getTag(ORE))).build(consumer, createLocation(resource, "smelting/ingot_from_ore"));
			CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(blocks.getTag(NETHER_ORE)), blocks.getBlock(ORE), 1F, 200).addCriterion("has_" + name + "_nether_ore", hasItem(blocks.getTag(NETHER_ORE))).build(consumer, createLocation(resource, "smelting/ore_from_nether_ore"));
			
			ShapedRecipeBuilder.shapedRecipe(blocks.getBlock(BLOCK)).key('#', items.getTag(INGOT)).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/block_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(GEAR)).key('#', items.getTag(INGOT)).patternLine(" # ").patternLine("# #").patternLine(" # ").addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(4), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/gear_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(ROD)).key('#', items.getTag(INGOT)).patternLine("#").patternLine("#").addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(2), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/rod_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(INGOT)).key('#', items.getTag(NUGGET)).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_nugget", hasItem(IntBound.atLeast(9), items.getTag(NUGGET))).build(consumer, createLocation(resource, "crafting/ingot_from_nugget"));
			
			ShapelessRecipeBuilder.shapelessRecipe(items.getItem(NUGGET), 9).addIngredient(items.getTag(INGOT)).addCriterion("has_" + name + "_nugget", hasItem(items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/nugget_from_ingot"));
			ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 9).addIngredient(blocks.getTag(BLOCK)).addCriterion("has_" + name + "_block", hasItem(blocks.getTag(BLOCK))).build(consumer, createLocation(resource, "crafting/ingot_from_block"));
			ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 4).addIngredient(items.getTag(GEAR)).addCriterion("has_" + name + "_gear", hasItem(items.getTag(GEAR))).build(consumer, createLocation(resource, "crafting/ingot_from_gear"));
			ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 2).addIngredient(items.getTag(ROD)).addCriterion("has_" + name + "_rod", hasItem(items.getTag(ROD))).build(consumer, createLocation(resource, "crafting/ingot_from_rod"));
			
			ShapedRecipeBuilder.shapedRecipe(items.getItem(HELMET)).key('#', items.getTag(INGOT)).patternLine("###").patternLine("# #").addCriterion("has_at_least_5_" + name + "_ingot", hasItem(IntBound.atLeast(5), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/helmet_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(CHESTPLATE)).key('#', items.getTag(INGOT)).patternLine("# #").patternLine("###").patternLine("###").addCriterion("has_at_least_8_" + name + "_ingot", hasItem(IntBound.atLeast(8), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/chestplate_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(LEGGINGS)).key('#', items.getTag(INGOT)).patternLine("###").patternLine("# #").patternLine("# #").addCriterion("has_at_least_7_" + name + "_ingot", hasItem(IntBound.atLeast(7), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/leggings_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(BOOTS)).key('#', items.getTag(INGOT)).patternLine("# #").patternLine("# #").addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(4), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/boots_from_ingot"));
			
			ShapedRecipeBuilder.shapedRecipe(items.getItem(AXE)).key('#', items.getTag(INGOT)).key('X', Items.STICK).patternLine("##").patternLine("#X").patternLine(" X").addCriterion("has_at_least_3_" + name + "_ingot", hasItem(IntBound.atLeast(3), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/axe_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(HOE)).key('#', items.getTag(INGOT)).key('X', Items.STICK).patternLine("##").patternLine(" X").patternLine(" X").addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(2), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/hoe_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(PICKAXE)).key('#', items.getTag(INGOT)).key('X', Items.STICK).patternLine("###").patternLine(" X ").patternLine(" X ").addCriterion("has_at_least_3_" + name + "_ingot", hasItem(IntBound.atLeast(3), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/pickaxe_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(SHOVEL)).key('#', items.getTag(INGOT)).key('X', Items.STICK).patternLine("#").patternLine("X").patternLine("X").addCriterion("has_at_least_1_" + name + "_ingot", hasItem(IntBound.atLeast(1), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/shovel_from_ingot"));
			ShapedRecipeBuilder.shapedRecipe(items.getItem(SWORD)).key('#', items.getTag(INGOT)).key('X', Items.STICK).patternLine("#").patternLine("#").patternLine("X").addCriterion("has_at_least_1_" + name + "_ingot", hasItem(IntBound.atLeast(1), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/sword_from_ingot"));
		});*/
	}
	
	private InventoryChangeTrigger.Instance hasItem(IntBound amount, Tag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).count(amount).build());
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(modid, resource.getName() + "/" + name);
	}
	
}
