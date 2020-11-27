package info.u_team.useful_resources.util;

import java.util.*;
import java.util.function.BiConsumer;

import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class FakeNameResource implements IResource {
	
	private final String name;
	private final IResource resource;
	
	public FakeNameResource(String name, IResource resource) {
		this.name = name;
		this.resource = resource;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public int getColor() {
		return resource.getColor();
	}
	
	@Override
	public ItemResourceType getRepairType() {
		return resource.getRepairType();
	}
	
	@Override
	public Map<BlockResourceType, RegistryEntry<Block>> getBlocks() {
		return resource.getBlocks();
	}
	
	@Override
	public Map<FluidResourceType, RegistryEntry<Fluid>> getFluids() {
		return resource.getFluids();
	}
	
	@Override
	public Map<ItemResourceType, RegistryEntry<Item>> getItems() {
		return resource.getItems();
	}
	
	@Override
	public List<RegistryEntry<Block>> getRegistryBlocks() {
		return resource.getRegistryBlocks();
	}
	
	@Override
	public List<RegistryEntry<Fluid>> getRegistryFluids() {
		return resource.getRegistryFluids();
	}
	
	@Override
	public List<RegistryEntry<Item>> getRegistryItems() {
		return resource.getRegistryItems();
	}
	
	@Override
	public IDeferredRegisterProvider getDeferredRegisterProvider() {
		return resource.getDeferredRegisterProvider();
	}
	
	@Override
	public void iterateRegistryBlocks(BiConsumer<BlockResourceType, Block> consumer) {
		resource.iterateRegistryBlocks(consumer);
	}
	
	@Override
	public void iterateRegistryFluids(BiConsumer<FluidResourceType, Fluid> consumer) {
		resource.iterateRegistryFluids(consumer);
	}
	
	@Override
	public void iterateRegistryItems(BiConsumer<ItemResourceType, Item> consumer) {
		resource.iterateRegistryItems(consumer);
	}
	
	@Override
	public <E extends IResourceType<?>, T extends IForgeRegistryEntry<T>> void iterateRegistry(List<RegistryEntry<T>> registryList, Map<E, RegistryEntry<T>> map, BiConsumer<E, RegistryEntry<T>> consumer) {
		resource.iterateRegistry(registryList, map, consumer);
	}
	
	@Override
	public IDataGeneratorConfigurator getDataGeneratorConfigurator() {
		return resource.getDataGeneratorConfigurator();
	}
	
	@Override
	public void addFeature(IResourceFeatureBuilder builder) {
		resource.addFeature(builder);
	}
}
