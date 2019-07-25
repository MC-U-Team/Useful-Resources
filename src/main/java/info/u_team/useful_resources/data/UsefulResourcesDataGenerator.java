package info.u_team.useful_resources.data;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.data.provider.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final DataGenerator generator = event.getGenerator();
		if (event.includeServer()) {
			generator.addProvider(new ResourceBlockStatesProvider(generator)); // Generate states
			generator.addProvider(new ResourceBlockModelsProvider(generator)); // Generate block models
			generator.addProvider(new ResourceItemModelsProvider(generator)); // Generate item models
			
			generator.addProvider(new ResourceBlockTagsProvider(generator)); // Generate block tags
			generator.addProvider(new ResourceItemTagsProvider(generator)); // Generate item tags
			
			generator.addProvider(new ResourceLootTableProvider(generator)); // Generate loot tables
			generator.addProvider(new ResourceRecipesProvider(generator)); // Generate recipes
		}
	}
	
}
