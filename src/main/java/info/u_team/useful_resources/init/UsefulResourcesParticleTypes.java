package info.u_team.useful_resources.init;

import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.particletype.BlockParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulResourcesParticleTypes {
	
	public static final CommonDeferredRegister<ParticleType<?>> PARTICLE_TYPES = CommonDeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UsefulResourcesMod.MODID);
	
	public static final RegistryObject<BlockParticleType> COLORED_BLOCK = PARTICLE_TYPES.register("colored_block", BlockParticleType::new);
	public static final RegistryObject<BlockParticleType> COLORED_OVERLAY_BLOCK = PARTICLE_TYPES.register("colored_overlay_block", BlockParticleType::new);
	
	public static void registerMod(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
	}
	
}
