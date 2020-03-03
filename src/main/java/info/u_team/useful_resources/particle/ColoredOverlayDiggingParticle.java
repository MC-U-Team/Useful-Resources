package info.u_team.useful_resources.particle;

import info.u_team.useful_resources.particle.ColoredOverlayBlockParticleData.Texture;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.model.data.EmptyModelData;

@OnlyIn(Dist.CLIENT)
public class ColoredOverlayDiggingParticle extends DiggingParticle {
	
	private boolean hasTint;
	
	public ColoredOverlayDiggingParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, BlockState state, Texture type) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, state);
		Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(state) //
				.getQuads(state, Direction.UP, rand, EmptyModelData.INSTANCE) //
				.stream() //
				.skip(type == Texture.FIRST ? 0 : 1) //
				.findFirst() //
				.filter(backed -> {
					hasTint = backed.hasTintIndex();
					return true;
				}) //
				.map(BakedQuad::func_187508_a) //
				.ifPresent(this::setSprite);
	}
	
	@Override
	protected void multiplyColor(BlockPos pos) {
		if (hasTint) {
			super.multiplyColor(pos);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<ColoredOverlayBlockParticleData> {
		
		public Particle makeParticle(ColoredOverlayBlockParticleData data, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ColoredOverlayDiggingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, data.getBlockState(), data.getTexture()).init();
		}
	}
}
