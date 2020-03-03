package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;

public class ResourceItemModelsProvider extends CommonItemModelsProvider {
	
	public ResourceItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		generateBaseModels();
		
		ResourceRegistry.getResources().stream().flatMap(resource -> resource.getBlocks().values().stream()).forEach(this::simpleBlock);
		//
		// ResourceRegistry.getResources().forEach(resource -> {
		// resource.getItems().values().forEach(item -> {
		// final String itemName = item.getRegistryName().getPath();
		// final String parent = item instanceof TieredItem ? "handheld" : "generated";
		//
		// getBuilder(itemName).parent(new UncheckedModelFile("item/" + parent)).texture("layer0", "item/" + resource.getName()
		// + "/" + itemName.replace(resource.getName() + "_", ""));
		// });
		// });
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
	}
}
