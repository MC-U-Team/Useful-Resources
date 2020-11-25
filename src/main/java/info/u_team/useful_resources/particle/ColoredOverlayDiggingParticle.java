package info.u_team.useful_resources.particle;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.*;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.model.data.EmptyModelData;

public class ColoredOverlayDiggingParticle extends Particle {
	
	private final BlockState sourceState;
	private BlockPos sourcePos;
	
	private float particleScale;
	
	private final float randU;
	private final float randV;
	
	private final List<Pair<TextureAtlasSprite, Integer>> sprites;
	private final HashMap<Integer, Triple<Float, Float, Float>> colors;
	
	public ColoredOverlayDiggingParticle(ClientWorld world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, BlockState state) {
		super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
		this.sourceState = state;
		particleScale = 0.1F * (rand.nextFloat() * 0.5F + 0.5F) * 2.0F;
		particleGravity = 1.0F;
		particleRed = 0.6F;
		particleGreen = 0.6F;
		particleBlue = 0.6F;
		particleScale /= 2.0F;
		randU = rand.nextFloat() * 3.0F;
		randV = rand.nextFloat() * 3.0F;
		sprites = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(state) //
				.getQuads(state, Direction.UP, rand, EmptyModelData.INSTANCE) //
				.stream() //
				.map(bakedQuad -> Pair.of(bakedQuad.getSprite(), bakedQuad.getTintIndex())) //
				.collect(Collectors.toList());
		colors = new HashMap<>();
		
	}
	
	public ColoredOverlayDiggingParticle setBlockPos(BlockPos pos) {
		sourcePos = pos;
		updateColors();
		return this;
	}
	
	public ColoredOverlayDiggingParticle init() {
		sourcePos = new BlockPos(this.posX, this.posY, this.posZ);
		updateColors();
		return this;
	}
	
	private void updateColors() {
		sprites.stream() //
				.map(Pair::getValue) //
				.filter(tintIndex -> tintIndex != -1) //
				.forEach(tintIndex -> {
					final int color = Minecraft.getInstance().getBlockColors().getColor(sourceState, world, sourcePos, tintIndex);
					final float red = particleRed * (color >> 16 & 255) / 255F;
					final float green = particleGreen * (color >> 8 & 255) / 255F;
					final float blue = particleBlue * (color & 255) / 255F;
					colors.put(tintIndex, Triple.of(red, green, blue));
				});
	}
	
	@Override
	public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
		final Vector3d projectedView = renderInfo.getProjectedView();
		
		final float renderX = (float) (MathHelper.lerp(partialTicks, prevPosX, posX) - projectedView.getX());
		final float renderY = (float) (MathHelper.lerp(partialTicks, prevPosY, posY) - projectedView.getY());
		final float renderZ = (float) (MathHelper.lerp(partialTicks, prevPosZ, posZ) - projectedView.getZ());
		
		final Quaternion quaternion;
		if (particleAngle == 0) {
			quaternion = renderInfo.getRotation();
		} else {
			quaternion = new Quaternion(renderInfo.getRotation());
			quaternion.multiply(Vector3f.ZP.rotation(MathHelper.lerp(partialTicks, prevParticleAngle, particleAngle)));
		}
		
		new Vector3f(-1, -1, 0).transform(quaternion);
		
		final Vector3f[] positionVectors = new Vector3f[] { new Vector3f(-1, -1, 0), new Vector3f(-1, 1, 0), new Vector3f(1, 1, 0), new Vector3f(1, -1, 0) };
		
		for (int index = 0; index < 4; ++index) {
			final Vector3f positionVector = positionVectors[index];
			positionVector.transform(quaternion);
			positionVector.mul(particleScale);
			positionVector.add(renderX, renderY, renderZ);
		}
		
		final int brightness = this.getBrightnessForRender(partialTicks);
		
		sprites.forEach(spritePair -> {
			final TextureAtlasSprite sprite = spritePair.getLeft();
			final int tintIndex = spritePair.getRight();
			
			final float red;
			final float green;
			final float blue;
			
			if (tintIndex == -1) {
				red = particleRed;
				green = particleGreen;
				blue = particleBlue;
			} else {
				final Triple<Float, Float, Float> colorTriple = colors.get(tintIndex);
				red = colorTriple.getLeft();
				green = colorTriple.getMiddle();
				blue = colorTriple.getRight();
			}
			
			final float minU = getMinU(sprite);
			final float maxU = getMaxU(sprite);
			final float minV = getMinV(sprite);
			final float maxV = getMaxV(sprite);
			
			buffer.pos(positionVectors[0].getX(), positionVectors[0].getY(), positionVectors[0].getZ()).tex(maxU, maxV).color(red, green, blue, particleAlpha).lightmap(brightness).endVertex();
			buffer.pos(positionVectors[1].getX(), positionVectors[1].getY(), positionVectors[1].getZ()).tex(maxU, minV).color(red, green, blue, particleAlpha).lightmap(brightness).endVertex();
			buffer.pos(positionVectors[2].getX(), positionVectors[2].getY(), positionVectors[2].getZ()).tex(minU, minV).color(red, green, blue, particleAlpha).lightmap(brightness).endVertex();
			buffer.pos(positionVectors[3].getX(), positionVectors[3].getY(), positionVectors[3].getZ()).tex(minU, maxV).color(red, green, blue, particleAlpha).lightmap(brightness).endVertex();
		});
	}
	
	private float getMinU(TextureAtlasSprite sprite) {
		return sprite.getInterpolatedU((double) ((randU + 1.0F) / 4.0F * 16.0F));
	}
	
	private float getMaxU(TextureAtlasSprite sprite) {
		return sprite.getInterpolatedU((double) (randU / 4.0F * 16.0F));
	}
	
	private float getMinV(TextureAtlasSprite sprite) {
		return sprite.getInterpolatedV((double) (randV / 4.0F * 16.0F));
	}
	
	private float getMaxV(TextureAtlasSprite sprite) {
		return sprite.getInterpolatedV((double) ((randV + 1.0F) / 4.0F * 16.0F));
	}
	
	@Override
	public Particle multiplyParticleScaleBy(float scale) {
		this.particleScale *= scale;
		return super.multiplyParticleScaleBy(scale);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public int getBrightnessForRender(float partialTick) {
		final int superValue = super.getBrightnessForRender(partialTick);
		int newValue = 0;
		if (world.isBlockLoaded(sourcePos)) {
			newValue = WorldRenderer.getCombinedLight(world, sourcePos);
		}
		return superValue == 0 ? newValue : superValue;
	}
	
	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.TERRAIN_SHEET;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BlockParticleData> {
		
		public Particle makeParticle(BlockParticleData data, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ColoredOverlayDiggingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, data.getBlockState()).init();
		}
	}
	
}
