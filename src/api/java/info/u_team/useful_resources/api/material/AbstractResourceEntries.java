package info.u_team.useful_resources.api.material;

import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public interface AbstractResourceEntries {
	
	record RegisteredRegistryEntry<T> (boolean registered, RegistryEntry<T> entry) {
	}
	
	Map<AbstractResourceType<? extends Block>, RegisteredRegistryEntry<? extends Block>> getBlocks();
	
	Map<AbstractResourceType<? extends Fluid>, RegisteredRegistryEntry<? extends Fluid>> getFluids();
	
	Map<AbstractResourceType<? extends Item>, RegisteredRegistryEntry<? extends Item>> getItems();
	
}
