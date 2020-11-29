package info.u_team.useful_resources.particletype;

import com.mojang.serialization.Codec;

import net.minecraft.particles.*;

public class BlockParticleType extends ParticleType<BlockParticleData> {
	
	public BlockParticleType() {
		super(false, BlockParticleData.DESERIALIZER);
	}
	
	@Override
	public Codec<BlockParticleData> func_230522_e_() {
		return BlockParticleData.func_239800_a_(this);
	}
	
}
