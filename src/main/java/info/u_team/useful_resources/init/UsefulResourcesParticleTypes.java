package info.u_team.useful_resources.init;

import info.u_team.u_team_core.particletype.UParticleType;
import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.particles.*;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesParticleTypes {
	
	public static final CommonDeferredRegister<ParticleType<?>> PARTICLE_TYPES = CommonDeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UsefulResourcesMod.MODID);
	
	public static final UParticleType<BlockParticleData> COLORED_OVERLAY_BLOCK = new UParticleType<>("colored_overlay_block", false, BlockParticleData.DESERIALIZER);
	
	public static void register(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
	}
}
