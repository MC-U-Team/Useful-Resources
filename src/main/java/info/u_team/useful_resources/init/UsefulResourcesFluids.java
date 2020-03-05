package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.util.RegistryUtil;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesFluids {
	
	@SubscribeEvent
	public static void register(Register<Fluid> event) {
		RegistryUtil.getAndApplyNames(resource -> resource.getFluids().values()).forEach(event.getRegistry()::register);
	}
	
}
