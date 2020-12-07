package info.u_team.useful_resources.data.resource;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.util.Util;

public class GenerationResources {
	
	static final Comparator<IResource> COMPARATOR = (a, b) -> a.getName().compareTo(b.getName());
	
	static final Set<IResource> RESOURCES = Util.make(new TreeSet<>(COMPARATOR), list -> ResourceRegistry.forEach(list::add));
	
	public static Set<IResource> getResources() {
		return Collections.unmodifiableSet(RESOURCES);
	}
	
	public static void forEach(Consumer<? super IResource> action) {
		RESOURCES.forEach(action);
	}
}
