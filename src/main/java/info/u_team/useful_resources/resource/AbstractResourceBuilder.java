package info.u_team.useful_resources.resource;

import java.util.function.Consumer;
import java.util.function.Supplier;

import info.u_team.useful_resources.api.registry.ExistingRegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class AbstractResourceBuilder {
	
	private final String name;
	private final int color;
	
	private final ResourceEntries entries;
	
	protected AbstractResourceBuilder(String name, int color, Consumer<ExistingResourceTypes> existingTypes) {
		this(name, color);
		final ExistingResourceTypes existingResourceTypes = new ExistingResourceTypes();
		existingTypes.accept(existingResourceTypes);
		entries.merge(existingResourceTypes.build());
	}
	
	private AbstractResourceBuilder(String name, int color) {
		this.name = name;
		this.color = color;
		entries = new ResourceEntries();
	}
	
	public final AbstractResourceBuilder add(AbstractResourceFeature feature) {
		entries.merge(feature.getEntries());
		return this;
	}
	
	protected abstract void apply(String name, int color);
	
	public final Resource build() {
		apply(name, color);
		return new Resource(name, color, entries.toImmutable());
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
	
}
