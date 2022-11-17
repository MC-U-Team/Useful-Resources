package info.u_team.useful_resources.resource;

import java.util.HashMap;
import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries.ExistingRegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceBuilder {
	
	private final String name;
	private final int color;
	
	private final Map<AbstractResourceType<? extends Block>, ExistingRegistryEntry<? extends Block>> blocks;
	private final Map<AbstractResourceType<? extends Fluid>, ExistingRegistryEntry<? extends Fluid>> fluids;
	private final Map<AbstractResourceType<? extends Item>, ExistingRegistryEntry<? extends Item>> items;
	
	protected ResourceBuilder(String name, int color) {
		this.name = name;
		this.color = color;
		blocks = new HashMap<>();
		fluids = new HashMap<>();
		items = new HashMap<>();
	}
	
	public final ResourceBuilder add(AbstractResourceFeature feature) {
		feature.getBlocks().forEach((type, entry) -> blocks.put(type, new ExistingRegistryEntry<>(entry)));
		return this;
	}
	
	public final ResourceBuilder addExistingBlock(AbstractResourceType<? extends Block> type, Block block) {
		blocks.put(type, new ExistingRegistryEntry<>(true, RegistryEntry.create(ForgeRegistries.BLOCKS.getKey(block), () -> block)));
		return this;
	}
	
	public final ResourceBuilder addExistingFluid(AbstractResourceType<? extends Fluid> type, Fluid fluid) {
		fluids.put(type, new ExistingRegistryEntry<>(true, RegistryEntry.create(ForgeRegistries.FLUIDS.getKey(fluid), () -> fluid)));
		return this;
	}
	
	public final ResourceBuilder addExistingItem(AbstractResourceType<? extends Item> type, Item item) {
		items.put(type, new ExistingRegistryEntry<>(true, RegistryEntry.create(ForgeRegistries.ITEMS.getKey(item), () -> item)));
		return this;
	}
	
	protected void apply() {
	}
	
	public final Resource build() {
		apply();
		return new Resource(name, color, new ResourceEntries(blocks, fluids, items));
	}
	
}
