package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.particle.ColoredOverlayDiggingParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UsefulResourcesParticles {
	
	@SubscribeEvent
	public static void register(ParticleFactoryRegisterEvent event) {
		final ParticleManager manager = Minecraft.getInstance().particles;
		
		manager.registerFactory(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK, new ColoredOverlayDiggingParticle.Factory());
	}
	
}
