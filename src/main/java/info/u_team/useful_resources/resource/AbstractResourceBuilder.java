package info.u_team.useful_resources.resource;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import info.u_team.useful_resources.api.registry.ExistingRegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import info.u_team.useful_resources.api.resource.ResourceRegistry;
import info.u_team.useful_resources.api.resource.datagen.AbstractResourceDataGenInfo;
import info.u_team.useful_resources.resource.register.RegisterProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractResourceBuilder {
	
	private final ResourceProperties properties;
	private final ResourceEntries entries;
	
	private final AbstractRegisterProvider registerProvider;
	
	private Supplier<AbstractResourceDataGenInfo> dataGenInfo;
	
	protected AbstractResourceBuilder(String name, int color, Rarity rarity, Consumer<ExistingResourceTypes> existingTypes) {
		this(name, color, rarity);
		final ExistingResourceTypes existingResourceTypes = new ExistingResourceTypes();
		existingTypes.accept(existingResourceTypes);
		entries.merge(existingResourceTypes.build());
	}
	
	protected AbstractResourceBuilder(String name, int color, Rarity rarity) {
		properties = new ResourceProperties(name, color, rarity);
		entries = new ResourceEntries();
		registerProvider = RegisterProvider.DEFAULT;
		dataGenInfo = AbstractResourceDataGenInfo::empty;
	}
	
	public final AbstractResourceBuilder add(ResourceFeatureCreator featureCreator) {
		final AbstractResourceFeature feature = featureCreator.create(properties, registerProvider);
		entries.merge(feature.getEntries());
		return this;
	}
	
	public final AbstractResourceBuilder setDataGenInfo(Supplier<AbstractResourceDataGenInfo> dataGenInfo) {
		this.dataGenInfo = Suppliers.memoize(dataGenInfo::get);
		return this;
	}
	
	protected abstract void apply(AbstractResourceEntries entries);
	
	public final Resource build() {
		apply(entries);
		return ResourceRegistry.register(new Resource(properties.name(), properties.color(), properties.rarity(), entries.toImmutable(), dataGenInfo));
	}
	
	public static class ExistingResourceTypes {
		
		private final ResourceEntries entries;
		
		private ExistingResourceTypes() {
			this.entries = new ResourceEntries();
		}
		
		public ExistingResourceTypes block(ResourceTypeKey<Block> type, Block entry) {
			block(type, ForgeRegistries.BLOCKS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes fluid(ResourceTypeKey<Fluid> type, Fluid entry) {
			fluid(type, ForgeRegistries.FLUIDS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes item(ResourceTypeKey<Item> type, Item entry) {
			item(type, ForgeRegistries.ITEMS.getKey(entry), () -> entry);
			return this;
		}
		
		public ExistingResourceTypes block(ResourceTypeKey<Block> type, ResourceLocation name, Supplier<? extends Block> entry) {
			entries.addBlock(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		public ExistingResourceTypes fluid(ResourceTypeKey<Fluid> type, ResourceLocation name, Supplier<? extends Fluid> entry) {
			entries.addFluid(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		public ExistingResourceTypes item(ResourceTypeKey<Item> type, ResourceLocation name, Supplier<? extends Item> entry) {
			entries.addItem(type, ExistingRegistryEntry.createExisting(name, entry));
			return this;
		}
		
		private AbstractResourceEntries build() {
			return entries.toImmutable();
		}
	}
	
	public static record ResourceProperties(String name, int color, Rarity rarity) {
	}
	
	@FunctionalInterface
	public static interface ResourceFeatureCreator {
		
		AbstractResourceFeature create(ResourceProperties properties, AbstractRegisterProvider registerProvider);
	}
	
}
