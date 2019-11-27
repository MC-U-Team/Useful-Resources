package info.u_team.useful_resources.data.provider;

import java.util.stream.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.BlockResourceType;
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
				} else {
					add(block, capitalize(block.getRegistryName().getPath().replace("_", " ")));
				}
			});
			resource.getItems().values().forEach(item -> {
				add(item, capitalize(item.getRegistryName().getPath().replace("_", " ")));
			});
		});
		
	}
	
	public String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
