package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.registry.RegistryEntry;
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
	
	Map<BlockResourceType, RegistryEntry<Block>> getBlocks();
	
	Map<FluidResourceType, RegistryEntry<Fluid>> getFluids();
	
	Map<ItemResourceType, RegistryEntry<Item>> getItems();
	
	List<RegistryEntry<Block>> getRegistryBlocks();
	
	List<RegistryEntry<Fluid>> getRegistryFluids();
	
	List<RegistryEntry<Item>> getRegistryItems();
	
	IDeferredRegisterProvider getDeferredRegisterProvider();
	
	default void iterateRegistryBlocks(BiConsumer<BlockResourceType, Block> consumer) {
		iterateRegistry(getRegistryBlocks(), getBlocks(), (type, entry) -> consumer.accept(type, entry.get()));
	}
	
	default void iterateRegistryFluids(BiConsumer<FluidResourceType, Fluid> consumer) {
		iterateRegistry(getRegistryFluids(), getFluids(), (type, entry) -> consumer.accept(type, entry.get()));
	}
	
	default void iterateRegistryItems(BiConsumer<ItemResourceType, Item> consumer) {
		iterateRegistry(getRegistryItems(), getItems(), (type, entry) -> consumer.accept(type, entry.get()));
	}
	
	default <E extends IResourceType<?>, T extends IForgeRegistryEntry<T>> void iterateRegistry(List<RegistryEntry<T>> registryList, Map<E, RegistryEntry<T>> map, BiConsumer<E, RegistryEntry<T>> consumer) {
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
	
	void addFeature(IResourceFeatureBuilder builder);
	
}
