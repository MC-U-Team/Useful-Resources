package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.UsefulResourcesMod.MODID;
import static info.u_team.useful_resources.type.ResourceBlockTypes.*;
import static info.u_team.useful_resources.type.ResourceItemTypes.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import info.u_team.u_team_core.data.CommonProvider;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.advancements.criterion.*;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.*;

public class ResourceRecipesProvider extends CommonProvider {
	
	public ResourceRecipesProvider(DataGenerator generator) {
		super("Resources-Recipes", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		final Path recipePath = path.resolve("recipes");
		final Path advancementPath = path.resolve("advancements");
		
		final Consumer<IFinishedRecipe> consumer = recipe -> {
			try {
				write(cache, recipe.getRecipeJson(), recipePath.resolve(recipe.getID().getPath() + ".json"));
				if (recipe.getAdvancementJson() != null) {
					write(cache, recipe.getAdvancementJson(), advancementPath.resolve(recipe.getID().getPath() + ".json"));
				}
			} catch (IOException ex) {
				LOGGER.error(marker, "Could not write data.", ex);
			}
		};
		
		Resources.getValues().forEach(resource -> registerResourceRecipes(resource, consumer));
	}
	
	private void registerResourceRecipes(IResource resource, Consumer<IFinishedRecipe> consumer) {
		final String name = resource.getName();
		final IResourceItems items = resource.getItems();
		final IResourceBlocks blocks = resource.getBlocks();
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(items.getTag(DUST)), items.getItem(INGOT), 0.5F, 100).addCriterion("has_" + name + "_dust", hasItem(items.getTag(DUST))).build(consumer, createLocation(resource, "blasting/ingot_from_dust"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(blocks.getTag(ORE)), items.getItem(INGOT), 0.7F, 100).addCriterion("has_" + name + "_ore", hasItem(blocks.getTag(ORE))).build(consumer, createLocation(resource, "blasting/ingot_from_ore"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(blocks.getTag(NETHER_ORE)), blocks.getBlock(ORE), 1F, 100).addCriterion("has_" + name + "_nether_ore", hasItem(blocks.getTag(NETHER_ORE))).build(consumer, createLocation(resource, "blasting/ore_from_nether_ores"));
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(items.getTag(DUST)), items.getItem(INGOT), 0.5F, 200).addCriterion("has_" + name + "_dust", hasItem(items.getTag(DUST))).build(consumer, createLocation(resource, "smelting/ingot_from_dust"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(blocks.getTag(ORE)), items.getItem(INGOT), 0.7F, 200).addCriterion("has_" + name + "_ore", hasItem(blocks.getTag(ORE))).build(consumer, createLocation(resource, "smelting/ingot_from_ore"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(blocks.getTag(NETHER_ORE)), blocks.getBlock(ORE), 1F, 200).addCriterion("has_" + name + "_nether_ore", hasItem(blocks.getTag(NETHER_ORE))).build(consumer, createLocation(resource, "smelting/ore_from_nether_ores"));
		
		ShapedRecipeBuilder.shapedRecipe(blocks.getBlock(BLOCK)).key('#', items.getTag(INGOT)).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/block_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getItem(GEAR)).key('#', items.getTag(INGOT)).patternLine(" # ").patternLine("# #").patternLine(" # ").addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/gear_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getItem(ROD)).key('#', items.getTag(INGOT)).patternLine("#").patternLine("#").addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getTag(INGOT))).build(consumer, createLocation(resource, "crafting/rod_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getItem(INGOT)).key('#', items.getTag(NUGGET)).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_nugget", hasItem(IntBound.atLeast(9), items.getTag(NUGGET))).build(consumer, createLocation(resource, "crafting/ingot_from_nugget"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getItem(NUGGET), 9).addIngredient(items.getTag(INGOT)).addCriterion("has_at_least_9_" + name + "_nugget", hasItem(IntBound.atLeast(9), items.getItem(NUGGET))).build(consumer, createLocation(resource, "crafting/nugget_from_ingot"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 9).addIngredient(blocks.getTag(BLOCK)).addCriterion("has_at_least_9_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getItem(INGOT))).build(consumer, createLocation(resource, "crafting/ingot_from_block"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 4).addIngredient(items.getTag(GEAR)).addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(4), items.getItem(INGOT))).build(consumer, createLocation(resource, "crafting/ingot_from_gear"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getItem(INGOT), 2).addIngredient(items.getTag(ROD)).addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(2), items.getItem(INGOT))).build(consumer, createLocation(resource, "crafting/ingot_from_rod"));
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveData(outputFolder, MODID);
	}
	
	private InventoryChangeTrigger.Instance hasItem(MinMaxBounds.IntBound amount, IItemProvider item) {
		return hasItem(ItemPredicate.Builder.create().item(item).count(amount).build());
	}
	
	private InventoryChangeTrigger.Instance hasItem(IntBound amount, Tag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).count(amount).build());
	}
	
	private InventoryChangeTrigger.Instance hasItem(Tag<Item> tag) {
		return hasItem(ItemPredicate.Builder.create().tag(tag).build());
	}
	
	private InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
		return new InventoryChangeTrigger.Instance(IntBound.UNBOUNDED, IntBound.UNBOUNDED, IntBound.UNBOUNDED, predicates);
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(UsefulResourcesMod.MODID, resource.getName() + "/" + name);
	}
	
}
