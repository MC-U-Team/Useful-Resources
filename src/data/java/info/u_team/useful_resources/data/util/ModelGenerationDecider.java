package info.u_team.useful_resources.data.util;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModelGenerationDecider<T extends IForgeRegistryEntry<T>> {
	
	public static <T extends IForgeRegistryEntry<T>> ModelGenerationDecider<T> create() {
		return new ModelGenerationDecider<T>();
	}
	
	private final Map<IResourceType<T>, ModelGenerationConsumer<T>> map;
	
	protected ModelGenerationDecider() {
		map = new LinkedHashMap<>();
	}
	
	public void addSpecial(IResourceType<T> type, ModelGenerationConsumer<T> consumer) {
		map.put(type, consumer);
	}
	
	public void generate(IResource resource, IResourceType<T> type, T entry, IDataGeneratorConfigurator configurator, Consumer<ResourceLocation> baseState) {
		generate(resource, type, entry, null, configurator, baseState);
	}
	
	public void generate(IResource resource, IResourceType<T> type, T entry, String basePath, IDataGeneratorConfigurator configurator, Consumer<ResourceLocation> baseState) {
		final ModelGenerationConsumer<T> consumer = map.get(type);
		final ResourceLocation baseModel;
		if (basePath == null) {
			baseModel = ModelGenerationUtil.getBaseModel(type, entry, configurator);
		} else {
			baseModel = ModelGenerationUtil.getBaseModel(type, basePath, configurator);
		}
		if (consumer != null) {
			consumer.accept(resource, type, entry, baseModel, configurator);
		} else {
			baseState.accept(baseModel);
		}
	}
	
	@FunctionalInterface
	public interface ModelGenerationConsumer<T extends IForgeRegistryEntry<T>> {
		
		void accept(IResource resource, IResourceType<T> type, T entry, ResourceLocation baseModel, IDataGeneratorConfigurator configurator);
		
	}
	
}
