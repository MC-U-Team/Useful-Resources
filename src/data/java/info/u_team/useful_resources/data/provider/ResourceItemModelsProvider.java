package info.u_team.useful_resources.data.provider;

import com.google.gson.JsonObject;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.data.resource.GenerationResources;
import info.u_team.useful_resources.data.util.*;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class ResourceItemModelsProvider extends CommonItemModelsProvider {
	
	public ResourceItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		// Generate base models
		generateBaseModels();
		
		// Define special block model generation
		final ModelGenerationDecider<Block> generationDeciderBlocks = ModelGenerationDecider.create();
		
		generationDeciderBlocks.addSpecial(BlockResourceType.BARS, this::blockItemModels);
		generationDeciderBlocks.addSpecial(BlockResourceType.CHAIN, this::blockItemModels);
		generationDeciderBlocks.addSpecial(BlockResourceType.FENCE, this::blockItemModels);
		generationDeciderBlocks.addSpecial(BlockResourceType.DOOR, this::blockItemModels);
		generationDeciderBlocks.addSpecial(BlockResourceType.TRAPDOOR, this::blockItemModels);
		
		// Generate block item models
		GenerationResources.forEachBlock((resource, type, block) -> {
			if (block.asItem() == Items.AIR) {
				return;
			}
			generationDeciderBlocks.generate(resource, type, block, resource.getDataGeneratorConfigurator(), baseModel -> {
				simpleBlock(block);
			});
		});
		
		// Define special item model generation
		final ModelGenerationDecider<Item> generationDeciderItems = ModelGenerationDecider.create();
		
		generationDeciderItems.addSpecial(ItemResourceType.MOLTEN_BUCKET, this::moltenBucket);
		
		// Generate item models
		GenerationResources.forEachItem((resource, type, item) -> {
			generationDeciderItems.generate(resource, type, item, resource.getDataGeneratorConfigurator(), baseModel -> {
				withExistingParent(getPath(item), baseModel);
			});
		});
	}
	
	private void generateBaseModels() {
		// Basic types
		withExistingParent("base/item/colored_generated_item", "generated") //
				.texture("layer0", modLoc("empty")) //
				.texture("layer1", "#colored");
		
		withExistingParent("base/item/colored_handheld_item", "handheld") //
				.texture("layer0", modLoc("empty")) //
				.texture("layer1", "#colored");
		
		withExistingParent("base/item/colored_overlay_generated_item", modLoc("base/item/colored_generated_item")) //
				.texture("layer0", "#uncolored");
		
		withExistingParent("base/item/colored_overlay_handheld_item", modLoc("base/item/colored_handheld_item")) //
				.texture("layer0", "#uncolored");
		
		// Special models for each type
		
		// ItemResourceType.CRUSHED_ORE
		withExistingParent("base/item/special/crushed_ore", modLoc("base/item/colored_overlay_generated_item")) //
				.texture("colored", "item/crushed_ore") //
				.texture("uncolored", "item/stone_crushed_ore_background");
		
		// ItemResourceType.CRUSHED_NETHER_ORE
		withExistingParent("base/item/special/crushed_nether_ore", modLoc("base/item/colored_overlay_generated_item")) //
				.texture("colored", "item/crushed_ore") //
				.texture("uncolored", "item/netherrack_crushed_ore_background");
		
		// ItemResourceType.CRUSHED_END_ORE
		withExistingParent("base/item/special/crushed_end_ore", modLoc("base/item/colored_overlay_generated_item")) //
				.texture("colored", "item/crushed_ore") //
				.texture("uncolored", "item/endstone_crushed_ore_background");
		
		// ItemResourceType.PURE_CRUSHED_ORE
		withExistingParent("base/item/special/pure_crushed_ore", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/pure_crushed_ore");
		
		// ItemResourceType.INGOT
		withExistingParent("base/item/special/ingot", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/ingot");
		
		// ItemResourceType.NUGGET
		withExistingParent("base/item/special/nugget", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/nugget");
		
		// ItemResourceType.GEM
		withExistingParent("base/item/special/gem", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/gem");
		
		// ItemResourceType.PIECE
		withExistingParent("base/item/special/piece", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/piece");
		
		// ItemResourceType.DUST
		withExistingParent("base/item/special/dust", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/dust");
		
		// ItemResourceType.PLATE
		withExistingParent("base/item/special/plate", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/plate");
		
		// ItemResourceType.DENSE_PLATE
		withExistingParent("base/item/special/dense_plate", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/dense_plate");
		
		// ItemResourceType.GEAR
		withExistingParent("base/item/special/gear", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/gear");
		
		// ItemResourceType.ROD
		withExistingParent("base/item/special/rod", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/rod");
		
		// ItemResourceType.AXE
		withExistingParent("base/item/special/axe", modLoc("base/item/colored_overlay_handheld_item")) //
				.texture("colored", "item/axe_material") //
				.texture("uncolored", "item/axe_handle");
		
		// ItemResourceType.HOE
		withExistingParent("base/item/special/hoe", modLoc("base/item/colored_overlay_handheld_item")) //
				.texture("colored", "item/hoe_material") //
				.texture("uncolored", "item/hoe_handle");
		
		// ItemResourceType.PICKAXE
		withExistingParent("base/item/special/pickaxe", modLoc("base/item/colored_overlay_handheld_item")) //
				.texture("colored", "item/pickaxe_material") //
				.texture("uncolored", "item/pickaxe_handle");
		
		// ItemResourceType.SHOVEL
		withExistingParent("base/item/special/shovel", modLoc("base/item/colored_overlay_handheld_item")) //
				.texture("colored", "item/shovel_material") //
				.texture("uncolored", "item/shovel_handle");
		
		// ItemResourceType.SWORD
		withExistingParent("base/item/special/sword", modLoc("base/item/colored_overlay_handheld_item")) //
				.texture("colored", "item/sword_material") //
				.texture("uncolored", "item/sword_handle");
		
		// ItemResourceType.HELMET
		withExistingParent("base/item/special/helmet", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/helmet");
		
		// ItemResourceType.CHESTPLATE
		withExistingParent("base/item/special/chestplate", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/chestplate");
		
		// ItemResourceType.LEGGINGS
		withExistingParent("base/item/special/leggings", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/leggings");
		
		// ItemResourceType.BOOTS
		withExistingParent("base/item/special/boots", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/boots");
		
		// ItemResourceType.HORSE_ARMOR
		withExistingParent("base/item/special/horse_armor", modLoc("base/item/colored_overlay_generated_item")) //
				.texture("colored", "item/horse_armor_material") //
				.texture("uncolored", "item/horse_armor_saddle");
		
		// BlockResourceType.BARS
		withExistingParent("base/item/special/bars", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "block/bars");
		
		// BlockResourceType.CHAIN
		withExistingParent("base/item/special/chain", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/chain");
		
		// BlockResourceType.FENCE
		getBuilder("base/item/special/fence").parent(new UncheckedModelFile(modLoc("base/block/special/fence_inventory")));
		
		// BlockResourceType.DOOR
		withExistingParent("base/item/special/door", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/door");
		
		// BlockResourceType.TRAPDOOR
		getBuilder("base/item/special/trapdoor").parent(new UncheckedModelFile(modLoc("base/block/special/trapdoor_bottom")));
	}
	
	public void moltenBucket(IResource resource, IResourceType<Item> type, Item item, ResourceLocation baseModel, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		generatedModels.computeIfAbsent(modLoc("item/" + getPath(item)), location -> new ItemModelBuilder(location, existingFileHelper) {
			
			@Override
			public JsonObject toJson() {
				final JsonObject root = new JsonObject();
				root.addProperty("parent", "forge:item/bucket_drip");
				root.addProperty("loader", "forge:bucket");
				root.addProperty("fluid", resource.getFluids().get(FluidResourceType.MOLTEN).get().getRegistryName().toString());
				return root;
			}
		});
	}
	
	public void blockItemModels(IResource resource, IResourceType<Block> type, Block block, ResourceLocation baseModel, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		withExistingParent(getPath(block), ModelGenerationUtil.getBaseModel(type, "item", resource.getDataGeneratorConfigurator()));
	}
}
