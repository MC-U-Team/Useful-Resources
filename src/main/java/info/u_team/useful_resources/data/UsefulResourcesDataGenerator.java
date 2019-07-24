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
		generator.addProvider(new BlockStatesProvider(generator));
		generator.addProvider(new BlockModelsProvider(generator));
		generator.addProvider(new ItemModelsProvider(generator));
	}
	
}
