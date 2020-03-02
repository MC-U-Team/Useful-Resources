package info.u_team.useful_resources.util;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import info.u_team.u_team_core.api.registry.IURegistryType;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RegistryUtil {
	
	public static <T extends IForgeRegistryEntry<T>> List<T> getAndApplyNames(Function<IResource, Collection<T>> function) {
		List<T> list = ResourceRegistry.getResources().stream().flatMap(resource -> function.apply(resource).stream()).collect(Collectors.toList());
		applyNames(list);
		return list;
	}
	
	private static <T extends IForgeRegistryEntry<T>> void applyNames(List<T> list) {
		list.stream().filter(entry -> entry instanceof IURegistryType) //
				.forEach(entry -> entry.setRegistryName(new ResourceLocation(UsefulResourcesMod.MODID, ((IURegistryType) entry).getEntryName())));
	}
	
}
