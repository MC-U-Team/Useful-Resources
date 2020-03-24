package info.u_team.useful_resources.api.feature;

import java.util.*;

import info.u_team.u_team_core.api.registry.IURegistryType;
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
	 * Returns a list of all blocks that are added with this feature. Can return an empty list. Cannot return null. The
	 * blocks must be named with {@link IURegistryType} or have a
	 * {@link Block#setRegistryName(net.minecraft.util.ResourceLocation)} set.
	 * 
	 * @return The collection of blocks
	 */
	Map<BlockResourceType, Block> getBlocks();
	
	/**
	 * Returns a list of all fluids that are added with this feature. Can return an empty list. Cannot return null. The
	 * blocks must be named with {@link IURegistryType} or have a
	 * {@link Block#setRegistryName(net.minecraft.util.ResourceLocation)} set.
	 * 
	 * @return The collection of blocks
	 */
	Map<FluidResourceType, Fluid> getFluids();
	
	/**
	 * Returns a list of all items that are added with this feature. Can return an empty list. Cannot return null. The items
	 * must be named with {@link IURegistryType} or have a {@link Item#setRegistryName(net.minecraft.util.ResourceLocation)}
	 * set.
	 * 
	 * @return The collection of blocks
	 */
	Map<ItemResourceType, Item> getItems();
	
	Collection<Block> getRegistryBlocks();
	
	Collection<Fluid> getRegistryFluids();
	
	Collection<Item> getRegistryItems();
	
}
