package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.util.RegistryUtil;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesItems {
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		RegistryUtil.getAndApplyNames(IResource::getRegistryItems).forEach(event.getRegistry()::register);
	}
	
}
