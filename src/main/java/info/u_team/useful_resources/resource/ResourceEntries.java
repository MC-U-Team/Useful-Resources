package info.u_team.useful_resources.resource;

import java.util.Collections;
import java.util.Map;

import info.u_team.useful_resources.api.material.AbstractResourceEntries;
import info.u_team.useful_resources.api.material.AbstractResourceType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ResourceEntries implements AbstractResourceEntries {
	
	private final Map<AbstractResourceType<? extends Block>, RegisteredRegistryEntry<? extends Block>> blocks;
	private final Map<AbstractResourceType<? extends Fluid>, RegisteredRegistryEntry<? extends Fluid>> fluids;
	private final Map<AbstractResourceType<? extends Item>, RegisteredRegistryEntry<? extends Item>> items;
	
	public ResourceEntries(Map<AbstractResourceType<? extends Block>, RegisteredRegistryEntry<? extends Block>> blocks, Map<AbstractResourceType<? extends Fluid>, RegisteredRegistryEntry<? extends Fluid>> fluids, Map<AbstractResourceType<? extends Item>, RegisteredRegistryEntry<? extends Item>> items) {
		this.blocks = Collections.unmodifiableMap(blocks);
		this.fluids = Collections.unmodifiableMap(fluids);
		this.items = Collections.unmodifiableMap(items);
	}
	
	@Override
	public Map<AbstractResourceType<? extends Block>, RegisteredRegistryEntry<? extends Block>> getBlocks() {
		return blocks;
	}
	
	@Override
	public Map<AbstractResourceType<? extends Fluid>, RegisteredRegistryEntry<? extends Fluid>> getFluids() {
		return fluids;
	}
	
	@Override
	public Map<AbstractResourceType<? extends Item>, RegisteredRegistryEntry<? extends Item>> getItems() {
		return items;
	}
	
}
