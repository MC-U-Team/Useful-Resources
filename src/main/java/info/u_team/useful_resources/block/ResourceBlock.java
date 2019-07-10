package info.u_team.useful_resources.block;

import java.util.function.Supplier;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.api.IResource;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ResourceBlock extends UBlock {
	
	private final Supplier<Float> hardness;
	private final Supplier<Float> resistance;
	
	public ResourceBlock(String type, IResource resource, Properties properties, Supplier<Float> hardness, Supplier<Float> resistance) {
		super(resource.getName() + "_" + type, UsefulResourcesItemGroups.GROUP, properties);
		this.hardness = hardness;
		this.resistance = resistance;
	}
	
	// We need to do the hardness and resistance that way, because the config is only present after the registration.
	
	@Override
	public float getBlockHardness(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
		return hardness.get();
	}
	
	@Override
	public float getExplosionResistance() {
		return resistance.get();
	}
	
}
