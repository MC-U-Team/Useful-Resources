package info.u_team.useful_resources.data.util;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class BlockStateGenerationDecider<T extends IForgeRegistryEntry<T>> {
	
	public static <T extends IForgeRegistryEntry<T>> BlockStateGenerationDecider<T> create() {
		return new BlockStateGenerationDecider<T>();
	}
	
	private final Map<IResourceType<T>, BlockStateGenerationConsumer<T>> map;
	
	protected BlockStateGenerationDecider() {
		map = new LinkedHashMap<>();
	}
	
	public void addSpecial(IResourceType<T> type, BlockStateGenerationConsumer<T> consumer) {
		map.put(type, consumer);
	}
	
	public void generate(IResourceType<T> type, T entry, IDataGeneratorConfigurator configurator, Consumer<ResourceLocation> baseState) {
		generate(type, entry, null, configurator, baseState);
	}
	
	public void generate(IResourceType<T> type, T entry, String basePath, IDataGeneratorConfigurator configurator, Consumer<ResourceLocation> baseState) {
		final BlockStateGenerationConsumer<T> consumer = map.get(type);
		final ResourceLocation baseModel;
		if (basePath == null) {
			baseModel = ModelGenerationUtil.getBaseModel(type, entry, configurator);
		} else {
			baseModel = ModelGenerationUtil.getBaseModel(type, basePath, configurator);
		}
		if (consumer != null) {
			consumer.accept(type, entry, baseModel, configurator);
		} else {
			baseState.accept(baseModel);
		}
	}
}
