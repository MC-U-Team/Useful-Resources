package info.u_team.useful_resources.block;

import java.util.Random;
import java.util.function.Function;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;
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
	
//	@Override // TODO
//	public BlockRenderLayer getRenderLayer() {
//		return BlockRenderLayer.CUTOUT;
//	}
	
	@Override
	public boolean addLandingEffects(BlockState state1, ServerWorld worldserver, BlockPos pos, BlockState state2, LivingEntity entity, int numberOfParticles) {
		return super.addLandingEffects(state1, worldserver, pos, state2, entity, numberOfParticles);
	}
	
	@Override
	public boolean addRunningEffects(BlockState state, World world, BlockPos pos, Entity entity) {
		return super.addRunningEffects(state, world, pos, entity);
	}
	
	@Override
	public boolean addHitEffects(BlockState state, World worldObj, RayTraceResult target, ParticleManager manager) {
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
	@Override
	public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) {
		return super.addDestroyEffects(state, world, pos, manager);
	}
}
