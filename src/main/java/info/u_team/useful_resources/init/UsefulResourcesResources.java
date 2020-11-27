package info.u_team.useful_resources.init;

import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulResourcesResources {
	
	public static void registerMod(IEventBus bus) {
		ResourceRegistry.forEach(resource -> resource.getDeferredRegisterProvider().register(bus));
	}
	
}
