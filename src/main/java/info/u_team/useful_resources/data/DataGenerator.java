package info.u_team.useful_resources.data;

import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class DataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		System.out.println("TESt");
	}
	
}
