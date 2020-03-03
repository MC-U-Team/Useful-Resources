package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.util.ResourceLocation;

public class ResourceItemModelsProvider extends CommonItemModelsProvider {
	
	public ResourceItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		generateBaseModels();
		
		ResourceRegistry.getResources().stream().flatMap(resource -> resource.getBlocks().values().stream()).forEach(this::simpleBlock);
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getItems().forEach((type, item) -> {
				withExistingParent(getPath(item), getBaseModel(type, resource.getDataGeneratorConfigurator()));
			});
		});
	}
	
	private ResourceLocation getBaseModel(ItemResourceType type, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		return modLoc("base/item/special/" + type.getName());
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
		
		// ItemResourceType.INGOT
		withExistingParent("base/item/special/ingot", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/ingot");
		
		// ItemResourceType.NUGGET
		withExistingParent("base/item/special/nugget", modLoc("base/item/colored_generated_item")) //
				.texture("colored", "item/nugget");
		
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
		
	}
}
