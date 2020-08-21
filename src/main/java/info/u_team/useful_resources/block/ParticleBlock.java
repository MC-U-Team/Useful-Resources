package info.u_team.useful_resources.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.init.UsefulResourcesParticleTypes;
import info.u_team.useful_resources.particle.ColoredOverlayDiggingParticle;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.*;

public abstract class ParticleBlock extends UBlock {
	
	public ParticleBlock(ItemGroup group, Properties properties, net.minecraft.item.Item.Properties blockItemProperties) {
		super(group, properties, blockItemProperties);
	}
	
	@Override
	public boolean addLandingEffects(BlockState stateUnused, ServerWorld world, BlockPos pos, BlockState state, LivingEntity entity, int numberOfParticles) {
		world.spawnParticle(new BlockParticleData(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK.get(), state), entity.getPosX(), entity.getPosY(), entity.getPosZ(), numberOfParticles, 0, 0, 0, 0.15);
		return true;
	}
	
	@Override
	public boolean addRunningEffects(BlockState state, World world, BlockPos pos, Entity entity) {
		final Vector3d motion = entity.getMotion();
		world.addParticle(new BlockParticleData(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK.get(), state), entity.getPosX() + (entity.rand.nextFloat() - 0.5) * entity.getWidth(), entity.getPosY() + 0.1, entity.getPosZ() + (entity.rand.nextFloat() - 0.5) * entity.getWidth(), motion.x * -4, 1.5, motion.z * -4);
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean addHitEffects(BlockState state, World world, RayTraceResult result, ParticleManager manager) {
		if (!(result instanceof BlockRayTraceResult)) {
			return false;
		}
		
		final BlockRayTraceResult blockResult = (BlockRayTraceResult) result;
		
		final BlockPos pos = blockResult.getPos();
		final Direction side = blockResult.getFace();
		final int x = pos.getX();
		final int y = pos.getY();
		final int z = pos.getZ();
		
		final AxisAlignedBB aabb = state.getShape(world, pos).getBoundingBox();
		
		double xCoord = (double) x + manager.rand.nextDouble() * (aabb.maxX - aabb.minX - (double) 0.2F) + (double) 0.1F + aabb.minX;
		double yCoord = (double) y + manager.rand.nextDouble() * (aabb.maxY - aabb.minY - (double) 0.2F) + (double) 0.1F + aabb.minY;
		double zCoord = (double) z + manager.rand.nextDouble() * (aabb.maxZ - aabb.minZ - (double) 0.2F) + (double) 0.1F + aabb.minZ;
		if (side == Direction.DOWN) {
			yCoord = y + aabb.minY - 0.1;
		}
		
		if (side == Direction.UP) {
			yCoord = y + aabb.maxY + 0.1;
		}
		
		if (side == Direction.NORTH) {
			zCoord = z + aabb.minZ - 0.1;
		}
		
		if (side == Direction.SOUTH) {
			zCoord = z + aabb.maxZ + 0.1;
		}
		
		if (side == Direction.WEST) {
			xCoord = x + aabb.minX - 0.1;
		}
		
		if (side == Direction.EAST) {
			xCoord = x + aabb.maxX + 0.1;
		}
		
		if (world instanceof ClientWorld) {
			manager.addEffect((new ColoredOverlayDiggingParticle((ClientWorld) world, xCoord, yCoord, zCoord, 0, 0, 0, state)).setBlockPos(pos).multiplyVelocity(0.2F).multiplyParticleScaleBy(0.6F));
		}
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) {
		state.getShape(world, pos).forEachBox((shapeMinX, shapeMinY, shapeMinZ, shapeMaxX, shapeMaxY, shapeMaxZ) -> {
			final double x = Math.min(1, shapeMaxX - shapeMinX);
			final double y = Math.min(1, shapeMaxY - shapeMinY);
			final double z = Math.min(1, shapeMaxZ - shapeMinZ);
			final int maxX = Math.max(2, MathHelper.ceil(x / 0.25));
			final int maxY = Math.max(2, MathHelper.ceil(y / 0.25));
			final int maxZ = Math.max(2, MathHelper.ceil(z / 0.25));
			
			for (int indexX = 0; indexX < maxX; ++indexX) {
				for (int indexY = 0; indexY < maxY; ++indexY) {
					for (int indexZ = 0; indexZ < maxZ; ++indexZ) {
						final double centerX = (indexX + 0.5) / maxX;
						final double centerY = (indexY + 0.5) / maxY;
						final double centerZ = (indexZ + 0.5) / maxZ;
						final double offsetX = centerX * x + shapeMinX;
						final double offsetY = centerY * y + shapeMinY;
						final double offsetZ = centerZ * z + shapeMinZ;
						if (world instanceof ClientWorld) {
							manager.addEffect((new ColoredOverlayDiggingParticle((ClientWorld) world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ, centerX - 0.5, centerY - 0.5, centerZ - 0.5, state)).setBlockPos(pos));
						}
					}
				}
			}
			
		});
		return true;
	}
}
