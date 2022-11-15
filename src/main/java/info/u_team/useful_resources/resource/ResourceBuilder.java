package info.u_team.useful_resources.resource;

import java.util.HashMap;
import java.util.Map;

import info.u_team.useful_resources.api.material.AbstractResourceEntries.RegisteredRegistryEntry;
import info.u_team.useful_resources.api.material.AbstractResourceType;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceBuilder {
	
	private final String name;
	private final int color;
	
	private final Map<AbstractResourceType<? extends Block>, RegisteredRegistryEntry<? extends Block>> blocks;
	private final Map<AbstractResourceType<? extends Fluid>, RegisteredRegistryEntry<? extends Fluid>> fluids;
	private final Map<AbstractResourceType<? extends Item>, RegisteredRegistryEntry<? extends Item>> items;
	
	protected ResourceBuilder(String name, int color) {
		this.name = name;
		this.color = color;
		blocks = new HashMap<>();
		fluids = new HashMap<>();
		items = new HashMap<>();
	}
	
	public final ResourceBuilder addBlock(AbstractResourceType<? extends Block> type, RegistryEntry<? extends Block> entry) {
		blocks.put(type, new RegisteredRegistryEntry<>(true, entry));
		return this;
	}
	
	public final ResourceBuilder addFluid(AbstractResourceType<? extends Fluid> type, RegistryEntry<? extends Fluid> entry) {
		fluids.put(type, new RegisteredRegistryEntry<>(true, entry));
		return this;
	}
	
	public final ResourceBuilder addItem(AbstractResourceType<? extends Item> type, RegistryEntry<? extends Item> entry) {
		items.put(type, new RegisteredRegistryEntry<>(true, entry));
		return this;
	}
	
	public final ResourceBuilder addExistingBlock(AbstractResourceType<? extends Block> type, Block block) {
		blocks.put(type, new RegisteredRegistryEntry<>(false, RegistryEntry.create(ForgeRegistries.BLOCKS.getKey(block), () -> block)));
		return this;
	}
	
	public final ResourceBuilder addExistingFluid(AbstractResourceType<? extends Fluid> type, Fluid fluid) {
		fluids.put(type, new RegisteredRegistryEntry<>(false, RegistryEntry.create(ForgeRegistries.FLUIDS.getKey(fluid), () -> fluid)));
		return this;
	}
	
	public final ResourceBuilder addExistingItem(AbstractResourceType<? extends Item> type, Item item) {
		items.put(type, new RegisteredRegistryEntry<>(false, RegistryEntry.create(ForgeRegistries.ITEMS.getKey(item), () -> item)));
		return this;
	}
	
	public final Resource build() {
		return new Resource(name, color, new ResourceEntries(blocks, fluids, items));
	}
	
}
