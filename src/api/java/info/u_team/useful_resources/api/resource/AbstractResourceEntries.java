package info.u_team.useful_resources.api.resource;

import java.util.Map;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public interface AbstractResourceEntries {
	
	public record ExistingRegistryEntry<T> (boolean existing, RegistryEntry<T> entry) {
		
		public ExistingRegistryEntry(RegistryEntry<T> entry) {
			this(false, entry);
		}
	}
	
	Map<ResourceTypeKey<? extends Block>, ExistingRegistryEntry<? extends Block>> getBlocks();
	
	Map<ResourceTypeKey<? extends Fluid>, ExistingRegistryEntry<? extends Fluid>> getFluids();
	
	Map<ResourceTypeKey<? extends Item>, ExistingRegistryEntry<? extends Item>> getItems();
	
}
