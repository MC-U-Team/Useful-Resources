package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.item.TieredItem;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class ResourceItemModelsProvider extends CommonItemModelsProvider {
	
	public ResourceItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		ResourceRegistry.getResources().stream().flatMap(resource -> resource.getBlocks().values().stream()).forEach(this::simpleBlock);
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getItems().values().forEach(item -> {
				final String itemName = item.getRegistryName().getPath();
				final String parent = item instanceof TieredItem ? "handheld" : "generated";
				
				getBuilder(itemName).parent(new UncheckedModelFile("item/" + parent)).texture("layer0", "item/" + resource.getName() + "/" + itemName.replace(resource.getName() + "_", ""));
			});
		});
	}
}
