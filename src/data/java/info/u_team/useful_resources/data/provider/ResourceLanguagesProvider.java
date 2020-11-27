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
		
		ResourceRegistry.forEach(resource -> {
			final String name = capitalize(resource.getName().replace("_", " "));
			resource.iterateRegistryBlocks((type, block) -> {
				if (type == BlockResourceType.NETHER_ORE) {
					add(block, "Nether " + name + " Ore");
				} else if (type == BlockResourceType.END_ORE) {
					add(block, "End " + name + " Ore");
				} else if (type == BlockResourceType.BLOCK) {
					add(block, "Block of " + name);
				} else if (type == BlockResourceType.MOLTEN_FLUID) {
					add(block, "Molten " + name);
				} else {
					add(block, capitalize(block.getRegistryName().getPath().replace("_", " ")));
				}
			});
			resource.iterateRegistryFluids((type, fluid) -> {
				if (type == FluidResourceType.MOLTEN || type == FluidResourceType.MOLTEN_FLOWING) {
					add(fluid, "Molten " + name);
				} else {
					add(fluid, capitalize(fluid.getRegistryName().getPath().replace("_", " ")));
				}
			});
			resource.iterateRegistryItems((type, item) -> {
				if (type == ItemResourceType.CRUSHED_ORE) {
					add(item, "Crushed " + name + " Ore");
				} else if (type == ItemResourceType.CRUSHED_NETHER_ORE) {
					add(item, "Crushed Nether " + name + " Ore");
				} else if (type == ItemResourceType.CRUSHED_END_ORE) {
					add(item, "Crushed End " + name + " Ore");
				} else if (type == ItemResourceType.PURE_CRUSHED_ORE) {
					add(item, "Pure Crushed " + name + " Ore");
				} else if (type == ItemResourceType.DENSE_PLATE) {
					add(item, "Dense " + name + " Plate");
				} else if (type == ItemResourceType.MOLTEN_BUCKET) {
					add(item, "Molten " + name + " Bucket");
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
