package info.u_team.useful_resources.data.util;

import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

@FunctionalInterface
public interface BlockStateGenerationConsumer<T extends IForgeRegistryEntry<T>> {
	
	void accept(IResourceType<T> type, T entry, ResourceLocation baseModel, IDataGeneratorConfigurator configurator);
	
}
