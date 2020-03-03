package info.u_team.useful_resources.block;

import java.util.Random;
import java.util.function.Function;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.init.*;
import info.u_team.useful_resources.particle.ColoredOverlayDiggingParticle;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.common.ToolType;

public class OreBlock extends UBlock {
	
	private final Function<Random, Integer> experienceDrop;
	
	public OreBlock(String name, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		super(name, UsefulResourcesItemGroups.GROUP, Properties.create(Material.ROCK).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).hardnessAndResistance(hardness, resistance), new Item.Properties().rarity(rarity));
		this.experienceDrop = experienceDrop;
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? experienceDrop.apply(RANDOM) : 0;
	}
	
	@Override
	public boolean addLandingEffects(BlockState stateUnused, ServerWorld world, BlockPos pos, BlockState state, LivingEntity entity, int numberOfParticles) {
		world.spawnParticle(new BlockParticleData(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK, state), entity.getPosX(), entity.getPosY(), entity.getPosZ(), numberOfParticles, 0, 0, 0, 0.15);
		return true;
	}
	
	@Override
	public boolean addRunningEffects(BlockState state, World world, BlockPos pos, Entity entity) {
		final Vec3d motion = entity.getMotion();
		world.addParticle(new BlockParticleData(UsefulResourcesParticleTypes.COLORED_OVERLAY_BLOCK, state), entity.getPosX() + (entity.rand.nextFloat() - 0.5) * entity.getWidth(), entity.getPosY() + 0.1, entity.getPosZ() + (entity.rand.nextFloat() - 0.5) * entity.getWidth(), motion.x * -4, 1.5, motion.z * -4);
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
		
		manager.addEffect((new ColoredOverlayDiggingParticle(world, xCoord, yCoord, zCoord, 0, 0, 0, state)).setBlockPos(pos).multiplyVelocity(0.2F).multipleParticleScaleBy(0.6F));
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) {
		return true;
	}
}
