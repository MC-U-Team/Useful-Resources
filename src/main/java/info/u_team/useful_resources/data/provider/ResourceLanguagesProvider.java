package info.u_team.useful_resources.data.provider;

import java.util.stream.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;

public class ResourceLanguagesProvider extends CommonLanguagesProvider {
	
	public ResourceLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		add(UsefulResourcesItemGroups.GROUP, "Useful Resources");
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().forEach((type, block) -> {
				if (type == BlockResourceType.BLOCK) {
					add(block, "Block of " + capitalize(resource.getName()));
				} else if (type == BlockResourceType.MOLTEN_FLUID) {
					add(block, "Molten " + capitalize(resource.getName()));
				} else {
					add(block, capitalize(block.getRegistryName().getPath().replace("_", " ")));
				}
			});
			resource.getFluids().forEach((type, fluid) -> {
				if (type == FluidResourceType.MOLTEN || type == FluidResourceType.MOLTEN_FLOWING) {
					add(fluid, "Molten " + capitalize(resource.getName()));
				} else {
					add(fluid, capitalize(fluid.getRegistryName().getPath().replace("_", " ")));
				}
			});
			resource.getItems().forEach((type, item) -> {
				if (type == ItemResourceType.MOLTEN_BUCKET) {
					add(item, "Molten " + capitalize(resource.getName() + " Bucket"));
				} else {
					add(item, capitalize(item.getRegistryName().getPath().replace("_", " ")));
				}
			});
		});
		
	}
	
	private String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
