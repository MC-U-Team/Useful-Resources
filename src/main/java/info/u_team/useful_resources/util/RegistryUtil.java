package info.u_team.useful_resources.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import info.u_team.u_team_core.api.registry.IURegistryType;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.resource.Resource;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

/**
 * Just an extension of {@link BaseRegistryUtil} with features extra for this ore mod
 * 
 * @author HyCraftHD
 *
 */
public class RegistryUtil {
	
	/**
	 * Get all resource blocks from all resources and apply names to blocks that implement {@link IURegistryType}
	 * 
	 * @return List with all blocks
	 */
	public static List<Block> getAllResourceBlocksAndApplyNames() {
		return getAndApplyNames(resource -> resource.getBlocks().getArray());
	}
	
	/**
	 * Get all resource items from all resources and apply names to items that implement {@link IURegistryType}
	 * 
	 * @return List with all items
	 */
	public static List<Item> getAllResourceItemsAndApplyNames() {
		return getAndApplyNames(resource -> resource.getItems().getArray());
	}
	
	/**
	 * General method to retrieve some {@link IForgeRegistryEntry} from {@link Resource}. In this case this can only be a
	 * {@link Block} or an {@link Item}. Apply names when the {@link IForgeRegistryEntry} implements {@link IURegistryType}.
	 * 
	 * @param <T> Type of {@link IForgeRegistryEntry}
	 * @param function Function to get {@link IForgeRegistryEntry} from {@link Resource}
	 * @return List with all {@link IForgeRegistryEntry}
	 */
	private static <T extends IForgeRegistryEntry<T>> List<T> getAndApplyNames(Function<IResource, T[]> function) {
		List<T> list = Resources.getValues().stream().flatMap(resource -> Arrays.stream(function.apply(resource))).collect(Collectors.toList());
		applyNames(list);
		return list;
	}
	
	/**
	 * Method to iterate over a list of {@link IForgeRegistryEntry}
	 * 
	 * @param <T> Type of {@link IForgeRegistryEntry}
	 * @param list List with entries where a name should be applied
	 */
	public static <T extends IForgeRegistryEntry<T>> void applyNames(List<T> list) {
		list.stream().filter(entry -> entry instanceof IURegistryType) //
				.forEach(entry -> entry.setRegistryName(new ResourceLocation(UsefulResourcesMod.MODID, ((IURegistryType) entry).getEntryName())));
	}
	
}
