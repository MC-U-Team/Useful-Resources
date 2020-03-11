package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import static net.minecraft.data.CookingRecipeBuilder.*;

import java.util.Map;
import java.util.function.Consumer;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.data.*;
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
		});
	}
	
	private final Tag<Item> getItemTag(BlockResourceType type, IResource resource) {
		return TagUtil.fromBlockTag(type.getTag(resource));
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(modid, resource.getName() + "/" + name);
	}
	
}
