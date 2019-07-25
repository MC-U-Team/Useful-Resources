package info.u_team.useful_resources.data.provider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Consumer;

import org.apache.logging.log4j.*;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.Resources;
import info.u_team.useful_resources.util.TagUtil;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.Tag;

public class ResourceRecipesProvider extends CommonProvider {
	
	private final Marker marker = MarkerManager.getMarker(getName());
	
	public ResourceRecipesProvider(DataGenerator generator) {
		super("Resources-Recipes", generator);
	}
	
	@Override
	public void act(DirectoryCache cache) throws IOException {
		final Path recipePath = path.resolve("recipes");
		final Path advancementPath = path.resolve("advancements");
		Resources.VALUES.forEach(resource -> {
			final Path recipeResourcePath = recipePath.resolve(resource.getName());
			final Path advancementResourcePath = advancementPath.resolve(resource.getName());
			registerResourceRecipes(resource, recipe -> {
				try {
					write(cache, recipe.getRecipeJson(), recipeResourcePath.resolve(recipe.getID().getPath() + ".json"));
					if (recipe.getAdvancementJson() != null) {
						write(cache, recipe.getAdvancementJson(), advancementResourcePath.resolve(recipe.getID().getPath() + ".json"));
					}
				} catch (IOException ex) {
					LOGGER.error(marker, "Could not write data.", ex);
				}
			});
			
		});
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
		
		final Tag<Item> oreTag = TagUtil.createItemTag("forge", "ore/" + name);
		final Tag<Item> netherOreTag = TagUtil.createItemTag("forge", "nether_ore/" + name);
		final Tag<Item> blockTag = TagUtil.createItemTag("forge", "storage_blocks/" + name);
		
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(dustTag), items.getIngot(), 0.5F, 100).addCriterion("has_" + resource.getName() + "_dust", hasItem(dustTag)).build(consumer, "blasting/ingot_from_dust");
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(oreTag), items.getIngot(), 0.7F, 100).addCriterion("has_" + resource.getName() + "_ore", hasItem(oreTag)).build(consumer, "blasting/ingot_from_ore");
		CookingRecipeBuilder.blastingRecipe(Ingredient.fromTag(netherOreTag), blocks.getOre(), 1F, 100).addCriterion("has_" + resource.getName() + "_nether_ore", hasItem(netherOreTag)).build(consumer, "blasting/ore_from_nether_ores");
		
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(dustTag), items.getIngot(), 0.5F, 200).addCriterion("has_" + resource.getName() + "_dust", hasItem(dustTag)).build(consumer, "blasting/ingot_from_dust");
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(oreTag), items.getIngot(), 0.7F, 200).addCriterion("has_" + resource.getName() + "_ore", hasItem(oreTag)).build(consumer, "blasting/ingot_from_ore");
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromTag(netherOreTag), blocks.getOre(), 1F, 200).addCriterion("has_" + resource.getName() + "_nether_ore", hasItem(netherOreTag)).build(consumer, "blasting/ore_from_nether_ores");
		
		ShapedRecipeBuilder.shapedRecipe(blocks.getBlock()).key('#', ingotTag).patternLine("###").patternLine("###").patternLine("###").build(consumer, "crafting/block_from_ingot");
		ShapedRecipeBuilder.shapedRecipe(items.getGear()).key('#', ingotTag).patternLine(" # ").patternLine("# #").patternLine(" # ").build(consumer, "crafting/gear_from_ingot");
		ShapedRecipeBuilder.shapedRecipe(items.getRod()).key('#', ingotTag).patternLine("#").patternLine("#").build(consumer, "crafting/rod_from_ingot");
		ShapelessRecipeBuilder.shapelessRecipe(items.getNugget(), 9).addIngredient(ingotTag).build(consumer, "crafting/nugget_from_ingot");
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 9).addIngredient(blockTag).build(consumer, "crafting/ingot_from_block");
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 4).addIngredient(gearTag).build(consumer, "crafting/ingot_from_gear");
		ShapelessRecipeBuilder.shapelessRecipe(items.getIngot(), 2).addIngredient(rodTag).build(consumer, "crafting/ingot_from_rod");
		ShapedRecipeBuilder.shapedRecipe(items.getIngot()).key('#', nuggetTag).patternLine("###").patternLine("###").patternLine("###").build(consumer, "crafting/block_from_nugget");
	}
	
	@Override
	protected Path resolvePath(Path outputFolder) {
		return resolveData(outputFolder);
	}
	
	// private EnterBlockTrigger.Instance enteredBlock(Block blockIn) {
	// return new EnterBlockTrigger.Instance(blockIn, (Map<IProperty<?>, Object>) null);
	// }
	//
	// private InventoryChangeTrigger.Instance hasItem(MinMaxBounds.IntBound amount, IItemProvider itemIn) {
	// return hasItem(ItemPredicate.Builder.create().item(itemIn).count(amount).build());
	// }
	//
	// private InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
	// return hasItem(ItemPredicate.Builder.create().item(itemIn).build());
	// }
	
	private InventoryChangeTrigger.Instance hasItem(Tag<Item> tagIn) {
		return hasItem(ItemPredicate.Builder.create().tag(tagIn).build());
	}
	
	private InventoryChangeTrigger.Instance hasItem(ItemPredicate... predicates) {
		return new InventoryChangeTrigger.Instance(MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED, predicates);
	}
	
}
