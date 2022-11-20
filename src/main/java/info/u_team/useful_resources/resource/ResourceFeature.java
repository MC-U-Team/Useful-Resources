package info.u_team.useful_resources.resource;

import java.util.function.BiFunction;
import java.util.function.Supplier;

import info.u_team.u_team_core.api.block.BlockItemProvider;
import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.RegistryObject;

public abstract class ResourceFeature implements AbstractResourceFeature {
	
	private final ResourceProperties resourceProperties;
	private final AbstractRegisterProvider registerProvider;
	private final ResourceEntries entries;
	
	protected ResourceFeature(ResourceProperties resourceProperties, AbstractRegisterProvider registerProvider) {
		this.resourceProperties = resourceProperties;
		this.registerProvider = registerProvider;
		entries = new ResourceEntries();
	}
	
	public <T extends Block & BlockItemProvider> RegistryEntry<? extends T> registerBlock(ResourceTypeKey<Block> type, Supplier<? extends T> supplier) {
		return registerBlock(type, this::defaultName, supplier);
	}
	
	public <T extends Block> RegistryEntry<? extends T> registerBlockOnly(ResourceTypeKey<Block> type, Supplier<? extends T> supplier) {
		return registerBlockOnly(type, this::defaultName, supplier);
	}
	
	public <T extends Fluid> RegistryEntry<? extends T> registerFluid(ResourceTypeKey<Fluid> type, Supplier<? extends T> supplier) {
		return registerFluid(type, this::defaultName, supplier);
	}
	
	public <T extends Item> RegistryEntry<? extends T> registerItem(ResourceTypeKey<Item> type, Supplier<? extends T> supplier) {
		return registerItem(type, this::defaultName, supplier);
	}
	
	public <T extends Block & BlockItemProvider> RegistryEntry<? extends T> registerBlock(ResourceTypeKey<Block> type, NameFunction<T> nameFunction, Supplier<? extends T> supplier) {
		final String name = nameFunction.apply(resourceProperties, type);
		final BlockRegistryObject<? extends T, BlockItem> registryObject = registerProvider.getBlockRegister().register(name, supplier);
		final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
		entries.addBlock(type, entry);
		return entry;
	}
	
	public <T extends Block> RegistryEntry<? extends T> registerBlockOnly(ResourceTypeKey<Block> type, NameFunction<T> nameFunction, Supplier<? extends T> supplier) {
		final String name = nameFunction.apply(resourceProperties, type);
		final RegistryObject<? extends T> registryObject = registerProvider.getBlockRegister().registerBlock(name, supplier);
		final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
		entries.addBlock(type, entry);
		return entry;
	}
	
	public <T extends Fluid> RegistryEntry<? extends T> registerFluid(ResourceTypeKey<Fluid> type, NameFunction<T> nameFunction, Supplier<? extends T> supplier) {
		final String name = nameFunction.apply(resourceProperties, type);
		final RegistryObject<? extends T> registryObject = registerProvider.getFluidRegister().register(name, supplier);
		final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
		entries.addFluid(type, entry);
		return entry;
	}
	
	public <T extends Item> RegistryEntry<? extends T> registerItem(ResourceTypeKey<Item> type, NameFunction<T> nameFunction, Supplier<? extends T> supplier) {
		final String name = nameFunction.apply(resourceProperties, type);
		final RegistryObject<? extends T> registryObject = registerProvider.getItemRegister().register(name, supplier);
		final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
		entries.addItem(type, entry);
		return entry;
	}
	
	public <T extends Block> RegistryEntry<? extends T> addBlock(ResourceTypeKey<Block> type, RegistryEntry<? extends T> entry) {
		entries.addBlock(type, entry);
		return entry;
	}
	
	public <T extends Fluid> RegistryEntry<? extends T> addFluid(ResourceTypeKey<Fluid> type, RegistryEntry<? extends T> entry) {
		entries.addFluid(type, entry);
		return entry;
	}
	
	public <T extends Item> RegistryEntry<? extends T> addItem(ResourceTypeKey<Item> type, RegistryEntry<? extends T> entry) {
		entries.addItem(type, entry);
		return entry;
	}
	
	protected String defaultName(ResourceProperties resourceProperties, ResourceTypeKey<?> type) {
		return type.getType().getDefaultRegistryName(resourceProperties.name());
	}
	
	@Override
	public final AbstractResourceEntries getEntries() {
		return entries;
	}
	
	@FunctionalInterface
	protected interface NameFunction<T> extends BiFunction<ResourceProperties, ResourceTypeKey<?>, String> {
	}
	
}
