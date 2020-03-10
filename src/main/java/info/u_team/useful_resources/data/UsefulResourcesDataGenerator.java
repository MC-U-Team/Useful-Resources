package info.u_team.useful_resources.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.data.provider.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(UsefulResourcesMod.MODID, event);
		if (event.includeClient()) {
			data.addProvider(ResourceBlockStatesProvider::new);
			data.addProvider(ResourceItemModelsProvider::new);
			data.addProvider(ResourceLanguagesProvider::new);
			data.addProvider(ResourceSpecialProvider::new);
		}
		if (event.includeServer()) {
			data.addProvider(ResourceBlockTagsProvider::new);
			data.addProvider(ResourceFluidTagsProvider::new);
			data.addProvider(ResourceItemTagsProvider::new);
			data.addProvider(ResourceLootTableProvider::new);
			data.addProvider(ResourceRecipesProvider::new);
			data.addProvider(ResourceWorldGenFeatureProvider::new);
		}
	}
}
