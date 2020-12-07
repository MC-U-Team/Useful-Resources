package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.FluidResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import java.util.function.Function;
import java.util.stream.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.data.resource.GenerationResources;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ResourceLanguagesProvider extends CommonLanguagesProvider {
	
	public ResourceLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		add(UsefulResourcesItemGroups.GROUP, "Useful Resources");
		
		GenerationResources.forEach(resource -> {
			final String name = capitalize(resource.getName().replace("_", " "));
			final Function<ForgeRegistryEntry<?>, String> defaultNameFunction = entry -> capitalize(entry.getRegistryName().getPath().replace("_", " "));
			resource.iterateRegistryBlocks((type, block) -> {
				if (type == NETHER_ORE) {
					add(block, "Nether " + name + " Ore");
				} else if (type == END_ORE) {
					add(block, "End " + name + " Ore");
				} else if (type == BLOCK) {
					add(block, "Block of " + name);
				} else if (type == MOLTEN_FLUID) {
					add(block, "Molten " + name);
				} else {
					add(block, defaultNameFunction.apply(block));
				}
			});
			resource.iterateRegistryFluids((type, fluid) -> {
				if (type == MOLTEN || type == MOLTEN_FLOWING) {
					add(fluid, "Molten " + name);
				} else {
					add(fluid, defaultNameFunction.apply(fluid));
				}
			});
			resource.iterateRegistryItems((type, item) -> {
				if (type == CRUSHED_ORE) {
					add(item, "Crushed " + name + " Ore");
				} else if (type == CRUSHED_NETHER_ORE) {
					add(item, "Crushed Nether " + name + " Ore");
				} else if (type == CRUSHED_END_ORE) {
					add(item, "Crushed End " + name + " Ore");
				} else if (type == PURE_CRUSHED_ORE) {
					add(item, "Pure Crushed " + name + " Ore");
				} else if (type == DENSE_PLATE) {
					add(item, "Dense " + name + " Plate");
				} else if (type == MOLTEN_BUCKET) {
					add(item, "Molten " + name + " Bucket");
				} else {
					add(item, defaultNameFunction.apply(item));
				}
			});
		});
		
	}
	
	private String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
