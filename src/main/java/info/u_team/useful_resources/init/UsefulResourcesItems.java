package info.u_team.useful_resources.init;

import java.util.Arrays;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesItems {
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		Resources.VALUES.stream().flatMap(resources -> Arrays.stream(resources.getItems().getArray())).forEach(event.getRegistry()::register);
	}
	
}
