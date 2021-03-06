package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesClearDataGeneratorConfig {
	
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		ResourceRegistry.getResources().stream().map(IResource::getDataGeneratorConfigurator).forEach(IDataGeneratorConfigurator::clearDataGeneratorConfig);
	}
	
}
