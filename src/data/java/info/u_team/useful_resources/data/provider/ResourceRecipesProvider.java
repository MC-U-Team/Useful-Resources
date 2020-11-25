package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static net.minecraft.data.CookingRecipeBuilder.*;
import static net.minecraft.data.ShapedRecipeBuilder.shapedRecipe;
import static net.minecraft.data.ShapelessRecipeBuilder.shapelessRecipe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.*;

import com.google.gson.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.block.Block;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.FalseCondition;

public class ResourceRecipesProvider extends CommonRecipesProvider {
	
	public ResourceRecipesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		// Remove vanilla recipes
		removeRecipe("iron_ingot", consumer);
		removeRecipe("iron_ingot_from_blasting", consumer);
		
		removeRecipe("gold_ingot", consumer);
		removeRecipe("gold_ingot_from_blasting", consumer);
		
		removeRecipe("diamond_from_smelting", consumer);
		removeRecipe("diamond_from_blasting", consumer);
		
		removeRecipe("emerald_from_smelting", consumer);
		removeRecipe("emerald_from_blasting", consumer);
		
		removeRecipe("lapis_from_smelting", consumer);
		removeRecipe("lapis_from_blasting", consumer);
		
		removeRecipe("quartz", consumer);
		removeRecipe("quartz_from_blasting", consumer);
		
		removeRecipe("coal_from_smelting", consumer);
		removeRecipe("coal_from_blasting", consumer);
		
		removeRecipe("redstone_from_smelting", consumer);
		removeRecipe("redstone_from_blasting", consumer);
		
		ResourceRegistry.getResources().forEach(resource -> {
			final Map<BlockResourceType, Block> blocks = resource.getBlocks().entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().get()));
			final Map<ItemResourceType, Item> items = resource.getItems().entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().get()));
			
			final Map<String, Object> extraProperties = resource.getDataGeneratorConfigurator().getExtraProperties();
			
			final ItemResourceType normalResourceType = getNormalResourceType(resource);
			final ItemResourceType tinyResourceType = getTinyResourceType(resource);
			
			final String normalResourceTypeName = normalResourceType.getName();
			final String tinyResourceTypeName = tinyResourceType.getName();
			
			// ORES -> INGOT / GEM
			if (shouldAddRecipeAllTypes(resource, ORE, NETHER_ORE, normalResourceType)) {
				final INamedTag<Item> oreTag = TagUtil.createItemTag("forge", "ores/" + resource.getName());
				final Item normalItem = items.get(normalResourceType);
				
				final float xp = ObjectUtil.getFloat(extraProperties.getOrDefault("oreSmeltingXp", 0.7F));
				
				smeltingRecipe(getIngredientOfTag(oreTag), normalItem, xp, 200) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "smelting/" + normalResourceTypeName + "_from_ore"));
				
				blastingRecipe(getIngredientOfTag(oreTag), normalItem, xp, 100) //
						.addCriterion("has_ore", hasItem(oreTag)) //
						.build(consumer, createLocation(resource, "blasting/" + normalResourceTypeName + "_from_ore"));
			}
			
			// DUST -> INGOT / GEM
			if (shouldAddRecipe(resource, DUST, normalResourceType)) {
				final INamedTag<Item> dustTag = DUST.getTag(resource);
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final Block blockBlock = blocks.get(BLOCK);
				shapedRecipe(blockBlock) //
						.key('#', normalTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/block_from_" + normalResourceTypeName));
				
				// BLOCK -> INGOT / GEM
				final INamedTag<Item> blockTag = getItemTag(BLOCK, resource);
				final Item normalItem = items.get(normalResourceType);
				
				shapelessRecipe(normalItem, 9) //
						.addIngredient(blockTag) //
						.addCriterion("has_block", hasItem(blockTag)) //
						.build(consumer, createLocation(resource, "crafting/" + normalResourceTypeName + "_from_block"));
			}
			
			// NUGGET / PIECE <-> INGOT / GEM
			if (shouldAddRecipe(resource, tinyResourceType, normalResourceType)) {
				// NUGGET / PIECE -> INGOT / GEM
				final INamedTag<Item> tinyTag = tinyResourceType.getTag(resource);
				final Item normalItem = items.get(normalResourceType);
				shapedRecipe(normalItem) //
						.key('#', tinyTag) //
						.patternLine("###") //
						.patternLine("###") //
						.patternLine("###") //
						.addCriterion("has_" + tinyResourceTypeName, hasItem(tinyTag)) //
						.build(consumer, createLocation(resource, "crafting/" + normalResourceTypeName + "_from_" + tinyResourceTypeName));
				
				// INGOT / GEM -> NUGGET / PIECE
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final Item tinyItem = items.get(tinyResourceType);
				
				shapelessRecipe(tinyItem, 9) //
						.addIngredient(normalTag) //
						.addCriterion("has_" + normalResourceTypeName, hasItem(normalTag)) //
						.build(consumer, createLocation(resource, "crafting/" + tinyResourceTypeName + "_from_" + normalResourceTypeName));
			}
			
			// INGOT / GEM -> AXE
			if (shouldAddRecipe(resource, normalResourceType, AXE)) {
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final INamedTag<Item> stickTag = Tags.Items.RODS_WOODEN;
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final INamedTag<Item> stickTag = Tags.Items.RODS_WOODEN;
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final INamedTag<Item> stickTag = Tags.Items.RODS_WOODEN;
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final INamedTag<Item> stickTag = Tags.Items.RODS_WOODEN;
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
				final INamedTag<Item> stickTag = Tags.Items.RODS_WOODEN;
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
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
				final INamedTag<Item> normalTag = normalResourceType.getTag(resource);
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
	
	private void removeRecipe(String name, Consumer<IFinishedRecipe> consumer) {
		removeRecipe(new ResourceLocation(name), consumer);
	}
	
	private void removeRecipe(ResourceLocation id, Consumer<IFinishedRecipe> consumer) {
		consumer.accept(new IFinishedRecipe() {
			
			@Override
			public JsonObject getRecipeJson() {
				final JsonObject json = new JsonObject();
				final JsonArray conditions = new JsonArray();
				conditions.add(CraftingHelper.serialize(FalseCondition.INSTANCE));
				json.add("conditions", conditions);
				return json;
			}
			
			@Override
			public void serialize(JsonObject json) {
			}
			
			@Override
			public IRecipeSerializer<?> getSerializer() {
				return null;
			}
			
			@Override
			public ResourceLocation getID() {
				return id;
			}
			
			@Override
			public JsonObject getAdvancementJson() {
				return null;
			}
			
			@Override
			public ResourceLocation getAdvancementID() {
				return null;
			}
		});
	}
	
	public static ItemResourceType getNormalResourceType(IResource resource) {
		final ResourceType type = resource.getDataGeneratorConfigurator().getResourceType();
		if (type == ResourceType.INGOT) {
			return INGOT;
		} else if (type == ResourceType.GEM) {
			return GEM;
		}
		return DUST;
	}
	
	public static ItemResourceType getTinyResourceType(IResource resource) {
		final ResourceType type = resource.getDataGeneratorConfigurator().getResourceType();
		if (type == ResourceType.INGOT) {
			return NUGGET;
		} else if (type == ResourceType.GEM) {
			return PIECE;
		}
		return PIECE;
	}
	
	public static boolean shouldAddRecipe(IResource resource, IResourceType<?>... types) {
		final boolean allTypes = shouldAddRecipeAllTypes(resource, types);
		final boolean anyRegistered = shouldAddRecipeAnyRegistered(resource, types);
		return allTypes && anyRegistered;
	}
	
	public static boolean shouldAddRecipeAllTypes(IResource resource, IResourceType<?>... types) {
		return Stream.of(types).allMatch(type -> {
			if (type instanceof BlockResourceType) {
				return resource.getBlocks().containsKey(type);
			} else if (type instanceof FluidResourceType) {
				return resource.getFluids().containsKey(type);
			} else if (type instanceof ItemResourceType) {
				return resource.getItems().containsKey(type);
			}
			return false;
		});
	}
	
	public static boolean shouldAddRecipeAnyRegistered(IResource resource, IResourceType<?>... types) {
		return Stream.of(types).anyMatch(type -> {
			if (type instanceof BlockResourceType) {
				return resource.getRegistryBlocks().contains(resource.getBlocks().get(type));
			} else if (type instanceof FluidResourceType) {
				return resource.getRegistryFluids().contains(resource.getFluids().get(type));
			} else if (type instanceof ItemResourceType) {
				return resource.getRegistryItems().contains(resource.getItems().get(type));
			}
			return false;
		});
	}
	
	private final INamedTag<Item> getItemTag(BlockResourceType type, IResource resource) {
		return TagUtil.fromBlockTag(type.getTag(resource));
	}
	
	private ResourceLocation createLocation(IResource resource, String name) {
		return new ResourceLocation(modid, resource.getName() + "/" + name);
	}
	
}
