package info.u_team.useful_resources.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import org.apache.logging.log4j.*;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.Resources;
import info.u_team.useful_resources.util.TagUtil;
import net.minecraft.advancements.criterion.*;
import net.minecraft.advancements.criterion.MinMaxBounds.IntBound;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;
import net.minecraft.util.*;

public class ResourceRecipesProvider extends CommonProvider {
	
	private final Marker marker = MarkerManager.getMarker(getName());
	
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
		
		Resources.VALUES.forEach(resource -> registerResourceRecipes(resource, consumer));
	}
	
	private void registerResourceRecipes(Resources resource, Consumer<IFinishedRecipe> consumer) {
		final String name = resource.getName();
		final IResourceItems items = resource.getItems();
		final IResourceBlocks blocks = resource.getBlocks();
		
		final Tag<Item> ingotTag = TagUtil.createItemTag("forge", "ingots/" + name);
		final Tag<Item> nuggetTag = TagUtil.createItemTag("forge", "nuggets/" + name);
		final Tag<Item> dustTag = TagUtil.createItemTag("forge", "dusts/" + name);
		final Tag<Item> gearTag = TagUtil.createItemTag("forge", "gears/" + name);
		final Tag<Item> rodTag = TagUtil.createItemTag("forge", "rods/" + name);
		
		final Tag<Item> oreTag = TagUtil.createItemTag("forge", "ores/" + name);
		final Tag<Item> netherOreTag = TagUtil.createItemTag("forge", "nether_ores/" + name);
		final Tag<Item> blockTag = TagUtil.createItemTag("forge", "storage_blocks/" + name);
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(dustTag), items.getIngot(), 0.5F, 100).addCriterion("has_" + name + "_dust", hasItem(dustTag)).build(consumer, createLocation(resource, "blasting/ingot_from_dust"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(oreTag), items.getIngot(), 0.7F, 100).addCriterion("has_" + name + "_ore", hasItem(oreTag)).build(consumer, createLocation(resource, "blasting/ingot_from_ore"));
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(netherOreTag), blocks.getOre(), 1F, 100).addCriterion("has_" + name + "_nether_ore", hasItem(netherOreTag)).build(consumer, createLocation(resource, "blasting/ore_from_nether_ores"));
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(dustTag), items.getIngot(), 0.5F, 200).addCriterion("has_" + name + "_dust", hasItem(dustTag)).build(consumer, createLocation(resource, "smelting/ingot_from_dust"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(oreTag), items.getIngot(), 0.7F, 200).addCriterion("has_" + name + "_ore", hasItem(oreTag)).build(consumer, createLocation(resource, "smelting/ingot_from_ore"));
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(netherOreTag), blocks.getOre(), 1F, 200).addCriterion("has_" + name + "_nether_ore", hasItem(netherOreTag)).build(consumer, createLocation(resource, "smelting/ore_from_nether_ores"));
		
		ShapedRecipeBuilder.shapedRecipe(blocks.getBlock()).key('#', ingotTag).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_ingot", hasItem(IntBound.atLeast(9), ingotTag)).build(consumer, createLocation(resource, "crafting/block_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getGear()).key('#', ingotTag).patternLine(" # ").patternLine("# #").patternLine(" # ").addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(9), ingotTag)).build(consumer, createLocation(resource, "crafting/gear_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getRod()).key('#', ingotTag).patternLine("#").patternLine("#").addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(9), ingotTag)).build(consumer, createLocation(resource, "crafting/rod_from_ingot"));
		ShapedRecipeBuilder.shapedRecipe(items.getIngot()).key('#', nuggetTag).patternLine("###").patternLine("###").patternLine("###").addCriterion("has_at_least_9_" + name + "_nugget", hasItem(IntBound.atLeast(9), nuggetTag)).build(consumer, createLocation(resource, "crafting/ingot_from_nugget"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getNugget(), 9).addIngredient(ingotTag).addCriterion("has_at_least_9_" + name + "_nugget", hasItem(IntBound.atLeast(9), items.getNugget())).build(consumer, createLocation(resource, "crafting/nugget_from_ingot"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 9).addIngredient(blockTag).addCriterion("has_at_least_9_" + name + "_ingot", hasItem(IntBound.atLeast(9), items.getIngot())).build(consumer, createLocation(resource, "crafting/ingot_from_block"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 4).addIngredient(gearTag).addCriterion("has_at_least_4_" + name + "_ingot", hasItem(IntBound.atLeast(4), items.getIngot())).build(consumer, createLocation(resource, "crafting/ingot_from_gear"));
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 2).addIngredient(rodTag).addCriterion("has_at_least_2_" + name + "_ingot", hasItem(IntBound.atLeast(2), items.getIngot())).build(consumer, createLocation(resource, "crafting/ingot_from_rod"));
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveData(outputFolder);
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
	
	private ResourceLocation createLocation(Resources resource, String name) {
		return new ResourceLocation(UsefulResourcesMod.MODID, resource.getName() + "/" + name);
	}
	
}
