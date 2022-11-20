package info.u_team.useful_resources.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.data.provider.UsefulResourcesBlockTagsProvider;
import info.u_team.useful_resources.data.provider.UsefulResourcesItemTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulReourcesDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(UsefulResourcesMod.MODID, event);
		
		data.addProvider(event.includeServer(), UsefulResourcesBlockTagsProvider::new, UsefulResourcesItemTagsProvider::new);
	}
}
