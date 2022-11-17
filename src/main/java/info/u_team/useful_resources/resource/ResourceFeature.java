package info.u_team.useful_resources.resource;

import java.util.HashMap;
import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.resource.AbstractResourceFeature;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ResourceFeature implements AbstractResourceFeature {
	
	private final Map<AbstractResourceType<? extends Block>, RegistryEntry<? extends Block>> blocks;
	private final Map<AbstractResourceType<? extends Fluid>, RegistryEntry<? extends Fluid>> fluids;
	private final Map<AbstractResourceType<? extends Item>, RegistryEntry<? extends Item>> items;
	
	public ResourceFeature() {
		blocks = new HashMap<>();
		fluids = new HashMap<>();
		items = new HashMap<>();
	}
	
	@Override
	public Map<AbstractResourceType<? extends Block>, RegistryEntry<? extends Block>> getBlocks() {
		return blocks;
	}
	
	@Override
	public Map<AbstractResourceType<? extends Fluid>, RegistryEntry<? extends Fluid>> getFluids() {
		return fluids;
	}
	
	@Override
	public Map<AbstractResourceType<? extends Item>, RegistryEntry<? extends Item>> getItems() {
		return items;
	}
	
}
