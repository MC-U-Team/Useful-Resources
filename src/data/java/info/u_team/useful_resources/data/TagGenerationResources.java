package info.u_team.useful_resources.data;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.resources.Resources;
import info.u_team.useful_resources.util.FakeNameResource;
import net.minecraft.util.Util;

public class TagGenerationResources {
	
	public static final List<IResource> RESOURCES = Util.make(new ArrayList<>(), list -> ResourceRegistry.getResources().forEach(list::add));
	
	public static void addAdditionalResources() {
		RESOURCES.add(new FakeNameResource("aluminium", Resources.ALUMINUM));
	}
	
	public static void forEach(Consumer<? super IResource> action) {
		RESOURCES.forEach(action);
	}
}
