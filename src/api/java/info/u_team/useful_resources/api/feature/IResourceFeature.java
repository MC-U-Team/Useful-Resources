package info.u_team.useful_resources.api.feature;

import java.util.*;

import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

/**
 * This interface represents a feature of a resource, like and ore feature, item feature, tool feature, etc.
 * 
 * @author HyCraftHD
 */
public interface IResourceFeature {
	
	/**
	 * Returns a list of all blocks that are added with this feature. Can return an empty list. Cannot return null.
	 * 
	 * @return The collection of blocks
	 */
	Map<BlockResourceType, RegistryEntry<? extends Block>> getBlocks();
	
	/**
	 * Returns a list of all fluids that are added with this feature. Can return an empty list. Cannot return null.
	 * 
	 * @return The collection of fluids
	 */
	Map<FluidResourceType, RegistryEntry<? extends Fluid>> getFluids();
	
	/**
	 * Returns a list of all items that are added with this feature. Can return an empty list. Cannot return null.
	 * 
	 * @return The collection of items
	 */
	Map<ItemResourceType, RegistryEntry<? extends Item>> getItems();
	
	List<RegistryEntry<? extends Block>> getRegistryBlocks();
	
	List<RegistryEntry<? extends Fluid>> getRegistryFluids();
	
	List<RegistryEntry<? extends Item>> getRegistryItems();
	
}
