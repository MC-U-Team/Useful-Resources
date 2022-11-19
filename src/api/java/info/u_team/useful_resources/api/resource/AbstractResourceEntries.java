package info.u_team.useful_resources.api.resource;

import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public interface AbstractResourceEntries {
	
	Map<ResourceTypeKey<Block>, RegistryEntry<? extends Block>> getBlocks();
	
	Map<ResourceTypeKey<Fluid>, RegistryEntry<? extends Fluid>> getFluids();
	
	Map<ResourceTypeKey<Item>, RegistryEntry<? extends Item>> getItems();
	
	AbstractResourceEntries toImmutable();
	
}
