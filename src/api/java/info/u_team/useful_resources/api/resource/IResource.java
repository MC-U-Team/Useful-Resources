package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import info.u_team.useful_resources.api.feature.IResourceFeatureBuilder;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IResource {
	
	String getName();
	
	int getColor();
	
	ItemResourceType getRepairType();
	
	Map<BlockResourceType, Block> getBlocks();
	
	Map<FluidResourceType, Fluid> getFluids();
	
	Map<ItemResourceType, Item> getItems();
	
	List<Block> getRegistryBlocks();
	
	List<Fluid> getRegistryFluids();
	
	List<Item> getRegistryItems();
	
	default void iterateRegistryBlocks(BiConsumer<BlockResourceType, Block> consumer) {
		iterateRegistry(getRegistryBlocks(), getBlocks(), consumer);
	}
	
	default void iterateRegistryFluids(BiConsumer<FluidResourceType, Fluid> consumer) {
		iterateRegistry(getRegistryFluids(), getFluids(), consumer);
	}
	
	default void iterateRegistryItems(BiConsumer<ItemResourceType, Item> consumer) {
		iterateRegistry(getRegistryItems(), getItems(), consumer);
	}
	
	default <E extends IResourceType<?>, T extends IForgeRegistryEntry<T>> void iterateRegistry(List<T> registryList, Map<E, T> map, BiConsumer<E, T> consumer) {
		registryList.stream().forEach(block -> {
			consumer.accept( //
					map //
							.entrySet() //
							.stream() //
							.filter(entry -> block.equals(entry.getValue())) //
							.map(Entry::getKey) //
							.findAny() //
							.get(), //
					block);
		});
	}
	
	IDataGeneratorConfigurator getDataGeneratorConfigurator();
	
	void clearDataGeneratorConfig();
	
	void addFeature(IResourceFeatureBuilder builder);
	
}
