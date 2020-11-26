package info.u_team.useful_resources.placement;

import java.util.Random;
import java.util.stream.Stream;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.placement.*;

public class EndIslandsPlacement extends SimplePlacement<NoPlacementConfig> {
	
	public EndIslandsPlacement() {
		super(NoPlacementConfig.CODEC);
	}
	
	@Override
	protected Stream<BlockPos> getPositions(Random random, NoPlacementConfig config, BlockPos pos) {
		return null;
	}
	
}
