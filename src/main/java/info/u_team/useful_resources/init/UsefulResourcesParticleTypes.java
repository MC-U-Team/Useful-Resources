package info.u_team.useful_resources.init;

import com.mojang.serialization.Codec;

import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.particles.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesParticleTypes {
	
	public static final CommonDeferredRegister<ParticleType<?>> PARTICLE_TYPES = CommonDeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, UsefulResourcesMod.MODID);
	
	public static final RegistryObject<ParticleType<BlockParticleData>> COLORED_OVERLAY_BLOCK = PARTICLE_TYPES.register("colored_overlay_block", () -> new ParticleType<BlockParticleData>(false, BlockParticleData.DESERIALIZER) {
		
		@Override
		public Codec<BlockParticleData> func_230522_e_() {
			return BlockParticleData.func_239800_a_(this);
		}
	});
	
	public static void register(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
	}
	
}
