package info.u_team.useful_resources.resource;

import java.util.function.Consumer;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.registry.ExistingRegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import info.u_team.useful_resources.api.resource.ResourceRegistry;
import info.u_team.useful_resources.resource.register.RegisterProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractResourceBuilder {
	
	private final ResourceProperties properties;
	private final ResourceEntries entries;
	private final RegisterProvider registerProvider;
	
	protected AbstractResourceBuilder(String name, int color, Consumer<ExistingResourceTypes> existingTypes) {
		this(name, color);
		final ExistingResourceTypes existingResourceTypes = new ExistingResourceTypes();
		existingTypes.accept(existingResourceTypes);
		entries.merge(existingResourceTypes.build());
	}
	
	private AbstractResourceBuilder(String name, int color) {
		properties = new ResourceProperties(name, color);
		entries = new ResourceEntries();
		registerProvider = new RegisterProvider();
	}
	
	public final AbstractResourceBuilder add(AbstractResourceFeatureCreator featureCreator) {
		final AbstractResourceFeature feature = featureCreator.create(properties, registerProvider);
		entries.merge(feature.getEntries());
		return this;
	}
	
	protected abstract void apply(ResourceEntries entries);
	
	public final Resource build() {
		apply(entries);
		return ResourceRegistry.register(new Resource(properties.name(), properties.color(), entries.toImmutable(), registerProvider));
	}
	
	public static class ExistingResourceTypes {
		
		private final ResourceEntries entries;
		
		private ExistingResourceTypes() {
			this.entries = new ResourceEntries();
		}
		
		public ExistingResourceTypes block(ResourceTypeKey<? extends Block> type, Block entry) {
			block(type, ForgeRegistries.BLOCKS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes fluid(ResourceTypeKey<? extends Fluid> type, Fluid entry) {
			fluid(type, ForgeRegistries.FLUIDS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes item(ResourceTypeKey<? extends Item> type, Item entry) {
			item(type, ForgeRegistries.ITEMS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes block(ResourceTypeKey<? extends Block> type, ResourceLocation name, Supplier<? extends Block> entry) {
			entries.addBlock(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		public ExistingResourceTypes fluid(ResourceTypeKey<? extends Fluid> type, ResourceLocation name, Supplier<? extends Fluid> entry) {
			entries.addFluid(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		public ExistingResourceTypes item(ResourceTypeKey<? extends Item> type, ResourceLocation name, Supplier<? extends Item> entry) {
			entries.addItem(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		private AbstractResourceEntries build() {
			return entries.toImmutable();
		}
	}
	
	public static record ResourceProperties(String name, int color) {
	}
	
	@FunctionalInterface
	public static interface AbstractResourceFeatureCreator {
		
		AbstractResourceFeature create(ResourceProperties properties, RegisterProvider registerProvider);
	}
	
}
