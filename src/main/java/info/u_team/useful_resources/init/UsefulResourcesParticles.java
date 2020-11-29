package info.u_team.useful_resources.init;

import info.u_team.useful_resources.particle.ColoredOverlayDiggingParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulResourcesParticles {
	
	private static void particleFactoryRegister(ParticleFactoryRegisterEvent event) {
		final ParticleManager manager = Minecraft.getInstance().particles;
		
		manager.registerFactory(UsefulResourcesParticleTypes.COLORED_BLOCK.get(), new ColoredOverlayDiggingParticle.Factory(true));
		manager.registerFactory(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK.get(), new ColoredOverlayDiggingParticle.Factory(false));
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesParticles::particleFactoryRegister);
	}
	
}
