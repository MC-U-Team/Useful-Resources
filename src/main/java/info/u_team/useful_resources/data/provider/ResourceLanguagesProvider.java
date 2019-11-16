package info.u_team.useful_resources.data.provider;

import java.util.stream.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import info.u_team.useful_resources.type.*;

public class ResourceLanguagesProvider extends CommonLanguagesProvider {
	
	public ResourceLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		add(UsefulResourcesItemGroups.GROUP, "Useful Resources");
		
		Resources.getValues().forEach(resource -> {
			Stream.of(resource.getBlocks().getArray()).forEach(block -> {
				if (block == resource.getBlocks().getBlock(ResourceBlockTypes.BLOCK)) {
					add(block, "Block of " + capitalize(resource.getName()));
				} else {
					add(block, capitalize(block.getRegistryName().getPath().replace("_", " ")));
				}
			});
			Stream.of(resource.getItems().getArray()).forEach(item -> {
				add(item, capitalize(item.getRegistryName().getPath().replace("_", " ")));
			});
		});
		
	}
	
	public String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
