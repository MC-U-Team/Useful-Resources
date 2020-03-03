package info.u_team.useful_resources.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;

import info.u_team.useful_resources.particle.ColoredOverlayBlockParticleData.Texture;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@OnlyIn(Dist.CLIENT)
public class ColoredOverlayDiggingParticle extends DiggingParticle {
	
	protected TextureAtlasSprite sprite_;
	
	private final float field_217587_G;
	private final float field_217588_H;
	
	private boolean hasTint;
	
	public ColoredOverlayDiggingParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, BlockState state, Texture type) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, state);
		
		field_217587_G = ObfuscationReflectionHelper.getPrivateValue(DiggingParticle.class, this, "field_217587_G");
		field_217588_H = ObfuscationReflectionHelper.getPrivateValue(DiggingParticle.class, this, "field_217588_H");
		
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
				.ifPresent(s -> sprite_ = s);
		
		System.out.println(sprite);
		System.out.println(sprite_);
	}
	
	@Override
	public void renderParticle(IVertexBuilder buffer, ActiveRenderInfo renderInfo, float partialTicks) {
		super.renderParticle(buffer, renderInfo, partialTicks);
		
		Vec3d vec3d = renderInfo.getProjectedView();
		float f = (float) (MathHelper.lerp((double) partialTicks, this.prevPosX, this.posX) - vec3d.getX());
		float f1 = (float) (MathHelper.lerp((double) partialTicks, this.prevPosY, this.posY) - vec3d.getY());
		float f2 = (float) (MathHelper.lerp((double) partialTicks, this.prevPosZ, this.posZ) - vec3d.getZ());
		Quaternion quaternion;
		if (this.particleAngle == 0.0F) {
			quaternion = renderInfo.getRotation();
		} else {
			quaternion = new Quaternion(renderInfo.getRotation());
			float f3 = MathHelper.lerp(partialTicks, this.prevParticleAngle, this.particleAngle);
			quaternion.multiply(Vector3f.ZP.rotation(f3));
		}
		
		Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
		vector3f1.transform(quaternion);
		Vector3f[] avector3f = new Vector3f[] { new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F) };
		float f4 = this.getScale(partialTicks);
		
		for (int i = 0; i < 4; ++i) {
			Vector3f vector3f = avector3f[i];
			vector3f.transform(quaternion);
			vector3f.mul(f4);
			vector3f.add(f, f1, f2);
		}
		
		float f7 = this.getMinU_();
		float f8 = this.getMaxU_();
		float f5 = this.getMinV_();
		float f6 = this.getMaxV_();
		int j = this.getBrightnessForRender(partialTicks);
		buffer.pos((double) avector3f[0].getX(), (double) avector3f[0].getY(), (double) avector3f[0].getZ()).tex(f8, f6).color(0.6F, 0.6F, 0.6F, 1).lightmap(j).endVertex();
		buffer.pos((double) avector3f[1].getX(), (double) avector3f[1].getY(), (double) avector3f[1].getZ()).tex(f8, f5).color(0.6F, 0.6F, 0.6F, 1).lightmap(j).endVertex();
		buffer.pos((double) avector3f[2].getX(), (double) avector3f[2].getY(), (double) avector3f[2].getZ()).tex(f7, f5).color(0.6F, 0.6F, 0.6F, 1).lightmap(j).endVertex();
		buffer.pos((double) avector3f[3].getX(), (double) avector3f[3].getY(), (double) avector3f[3].getZ()).tex(f7, f6).color(0.6F, 0.6F, 0.6F, 1).lightmap(j).endVertex();
	}
	
	protected float getMinU_() {
		return this.sprite_.getInterpolatedU((double) ((this.field_217587_G + 1.0F) / 4.0F * 16.0F));
	}
	
	protected float getMaxU_() {
		return this.sprite_.getInterpolatedU((double) (this.field_217587_G / 4.0F * 16.0F));
	}
	
	protected float getMinV_() {
		return this.sprite_.getInterpolatedV((double) (this.field_217588_H / 4.0F * 16.0F));
	}
	
	protected float getMaxV_() {
		return this.sprite_.getInterpolatedV((double) ((this.field_217588_H + 1.0F) / 4.0F * 16.0F));
	}
	
	@Override
	protected void multiplyColor(BlockPos pos) {
		// if (hasTint) {
		super.multiplyColor(pos);
		// }
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<ColoredOverlayBlockParticleData> {
		
		public Particle makeParticle(ColoredOverlayBlockParticleData data, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new ColoredOverlayDiggingParticle(world, x, y, z, xSpeed, ySpeed, zSpeed, data.getBlockState(), data.getTexture()).init();
		}
	}
}
