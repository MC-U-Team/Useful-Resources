package info.u_team.useful_resources.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import info.u_team.useful_resources.init.UsefulResourcesParticleTypes;
import net.minecraft.block.*;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.*;
import net.minecraftforge.registries.ForgeRegistries;

public class ColoredOverlayBlockParticleData implements IParticleData {
	
	private final ParticleType<ColoredOverlayBlockParticleData> particleType;
	private final BlockState state;
	private final Texture texture;
	
	public ColoredOverlayBlockParticleData(BlockState state, Texture texture) {
		this(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK, state, texture);
	}
	
	public ColoredOverlayBlockParticleData(ParticleType<ColoredOverlayBlockParticleData> particleType, BlockState state, Texture texture) {
		this.particleType = particleType;
		this.state = state;
		this.texture = texture;
	}
	
	@Override
	public void write(PacketBuffer buffer) {
		buffer.writeVarInt(Block.getStateId(state));
		buffer.writeEnumValue(texture);
	}
	
	@Override
	public String getParameters() {
		return ForgeRegistries.PARTICLE_TYPES.getKey(getType()) + " " + BlockStateParser.toString(this.state) + " " + texture;
	}
	
	@Override
	public ParticleType<ColoredOverlayBlockParticleData> getType() {
		return particleType;
	}
	
	public BlockState getBlockState() {
		return state;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public static enum Texture {
		FIRST,
		SECOND;
	}
	
	public static final IDeserializer<ColoredOverlayBlockParticleData> DESERIALIZER = new IDeserializer<ColoredOverlayBlockParticleData>() {
		
		public ColoredOverlayBlockParticleData deserialize(ParticleType<ColoredOverlayBlockParticleData> particleType, StringReader reader) throws CommandSyntaxException {
			reader.expect(' ');
			final BlockState state = (new BlockStateParser(reader, false)).parse(false).getState();
			reader.expect(' ');
			final Texture texture = Texture.valueOf(reader.readString());
			return new ColoredOverlayBlockParticleData(particleType, state, texture);
		}
		
		public ColoredOverlayBlockParticleData read(ParticleType<ColoredOverlayBlockParticleData> particleType, PacketBuffer buffer) {
			return new ColoredOverlayBlockParticleData(particleType, Block.getStateById(buffer.readVarInt()), buffer.readEnumValue(Texture.class));
		}
	};
}
