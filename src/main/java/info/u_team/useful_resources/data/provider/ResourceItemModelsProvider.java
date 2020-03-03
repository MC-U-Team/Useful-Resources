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
				.texture("layer1", "#colored");
		
		withExistingParent("base/item/colored_handheld_item", "handheld") //
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
	}
}
