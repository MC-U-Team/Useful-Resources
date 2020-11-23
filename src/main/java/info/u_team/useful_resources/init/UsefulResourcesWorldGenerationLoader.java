package info.u_team.useful_resources.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class UsefulResourcesWorldGenerationLoader {
	
	private static void setup(FMLCommonSetupEvent event) {
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesWorldGenerationLoader::setup);
	}
	
}
