package info.u_team.useful_resources.init;

import info.u_team.u_team_core.particletype.UParticleType;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.particles.*;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesParticleTypes {
	
	public static final UParticleType<BlockParticleData> COLORED_OVERLAY_BLOCK = new UParticleType<>("colored_overlay_block", false, BlockParticleData.DESERIALIZER);
	
	@SubscribeEvent
	public static void register(Register<ParticleType<?>> event) {
		BaseRegistryUtil.getAllGenericRegistryEntriesAndApplyNames(UsefulResourcesMod.MODID, ParticleType.class).forEach(event.getRegistry()::register);
	}
	
}
