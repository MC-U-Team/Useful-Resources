package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.feature.IResourceFeatureBuilder;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.*;

public interface IResource {
	
	static final BlockDeferredRegister BLOCKS = BlockDeferredRegister.create(UsefulResourcesMod.MODID);
	static final CommonDeferredRegister<Fluid> FLUIDS = CommonDeferredRegister.create(ForgeRegistries.FLUIDS, UsefulResourcesMod.MODID);
	static final CommonDeferredRegister<Item> ITEMS = CommonDeferredRegister.create(ForgeRegistries.ITEMS, UsefulResourcesMod.MODID);
	
	String getName();
	
	int getColor();
	
	ItemResourceType getRepairType();
	
	Map<BlockResourceType, RegistryEntry<Block>> getBlocks();
	
	Map<FluidResourceType, RegistryEntry<Fluid>> getFluids();
	
	Map<ItemResourceType, RegistryEntry<Item>> getItems();
	
	List<RegistryEntry<Block>> getRegistryBlocks();
	
	List<RegistryEntry<Fluid>> getRegistryFluids();
	
	List<RegistryEntry<Item>> getRegistryItems();
	
	default void iterateRegistryBlocks(BiConsumer<BlockResourceType, RegistryEntry<Block>> consumer) {
		iterateRegistry(getRegistryBlocks(), getBlocks(), consumer);
	}
	
	default void iterateRegistryFluids(BiConsumer<FluidResourceType, RegistryEntry<Fluid>> consumer) {
		iterateRegistry(getRegistryFluids(), getFluids(), consumer);
	}
	
	default void iterateRegistryItems(BiConsumer<ItemResourceType, RegistryEntry<Item>> consumer) {
		iterateRegistry(getRegistryItems(), getItems(), consumer);
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
	
	void clearDataGeneratorConfig();
	
	void addFeature(IResourceFeatureBuilder builder);
	
}
