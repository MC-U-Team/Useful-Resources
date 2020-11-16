package info.u_team.useful_resources.init;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulResourcesResources {
	
	public static void registerMod(IEventBus bus) {
		IResource.DEFERRED_PROVIDER.register(bus);
	}
	
}
