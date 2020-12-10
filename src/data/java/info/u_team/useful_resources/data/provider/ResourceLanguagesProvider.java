package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.FluidResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import java.util.function.Function;
import java.util.stream.*;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.data.resource.GenerationResources;
import info.u_team.useful_resources.data.util.LanguageGenerationDecider;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ResourceLanguagesProvider extends CommonLanguagesProvider {
	
	public ResourceLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		add(UsefulResourcesItemGroups.GROUP, "Useful Resources");
		
		final LanguageGenerationDecider languageDecider = LanguageGenerationDecider.create();
		
		languageDecider.addSpecialBlock(NETHER_ORE, (resource, type, block, name, configurator) -> {
			add(block, "Nether " + name + " Ore");
		});
		languageDecider.addSpecialBlock(END_ORE, (resource, type, block, name, configurator) -> {
			add(block, "End " + name + " Ore");
		});
		languageDecider.addSpecialBlock(BLOCK, (resource, type, block, name, configurator) -> {
			add(block, "Block of " + name);
		});
		languageDecider.addSpecialBlock(MOLTEN_FLUID, (resource, type, block, name, configurator) -> {
			add(block, "Molten " + name);
		});
		
		languageDecider.addSpecialFluid(MOLTEN, (resource, type, fluid, name, configurator) -> {
			add(fluid, "Molten " + name);
		});
		languageDecider.addSpecialFluid(MOLTEN_FLOWING, (resource, type, fluid, name, configurator) -> {
			add(fluid, "Molten " + name);
		});
		
		languageDecider.addSpecialItem(CRUSHED_ORE, (resource, type, item, name, configurator) -> {
			add(item, "Crushed " + name + " Ore");
		});
		languageDecider.addSpecialItem(CRUSHED_NETHER_ORE, (resource, type, item, name, configurator) -> {
			add(item, "Crushed Nether " + name + " Ore");
		});
		languageDecider.addSpecialItem(CRUSHED_END_ORE, (resource, type, item, name, configurator) -> {
			add(item, "Crushed End " + name + " Ore");
		});
		languageDecider.addSpecialItem(PURE_CRUSHED_ORE, (resource, type, item, name, configurator) -> {
			add(item, "Pure Crushed " + name + " Ore");
		});
		languageDecider.addSpecialItem(DENSE_PLATE, (resource, type, item, name, configurator) -> {
			add(item, "Dense " + name + " Plate");
		});
		languageDecider.addSpecialItem(MOLTEN_BUCKET, (resource, type, item, name, configurator) -> {
			add(item, "Molten " + name + " Bucket");
		});
		
		GenerationResources.forEach(resource -> {
			
			final String name = capitalize(resource.getName().replace("_", " "));
			final Function<ForgeRegistryEntry<?>, String> defaultNameFunction = entry -> capitalize(entry.getRegistryName().getPath().replace("_", " "));
			
			resource.iterateRegistryBlocks((type, block) -> {
				languageDecider.generate(resource, type, block, name, resource.getDataGeneratorConfigurator(), entry -> {
					add(entry, defaultNameFunction.apply(block));
				});
			});
			
			resource.iterateRegistryFluids((type, fluid) -> {
				languageDecider.generate(resource, type, fluid, name, resource.getDataGeneratorConfigurator(), entry -> {
					add(entry, defaultNameFunction.apply(fluid));
				});
			});
			
			resource.iterateRegistryItems((type, item) -> {
				languageDecider.generate(resource, type, item, name, resource.getDataGeneratorConfigurator(), entry -> {
					add(entry, defaultNameFunction.apply(item));
				});
			});
		});
		
	}
	
	private String capitalize(String words) {
		return Stream.of(words.trim().split("\\s")).filter(word -> word.length() > 0).map(word -> word.substring(0, 1).toUpperCase() + word.substring(1)).collect(Collectors.joining(" "));
	}
}
