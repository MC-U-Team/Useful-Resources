package info.u_team.useful_resources.data.provider;

import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import net.minecraft.item.TieredItem;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class ResourceItemModelsProvider extends CommonItemModelsProvider {
	
	public ResourceItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		Resources.getValues().stream().flatMap(resource -> Stream.of(resource.getBlocks().getArray())).forEach(this::simpleBlock);
		
		Resources.getValues().forEach(resource -> {
			Stream.of(resource.getItems().getArray()).forEach(item -> {
				final String itemName = item.getRegistryName().getPath();
				final String parent = item instanceof TieredItem ? "handheld" : "generated";
				
				getBuilder(itemName).parent(new UncheckedModelFile("item/" + parent)).texture("layer0", "item/" + resource.getName() + "/" + itemName.replace(resource.getName() + "_", ""));
			});
		});
	}
}
