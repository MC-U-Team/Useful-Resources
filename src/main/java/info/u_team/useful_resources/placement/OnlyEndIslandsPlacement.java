package info.u_team.useful_resources.placement;

import java.util.Random;
import java.util.stream.Stream;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.gen.placement.*;

public class OnlyEndIslandsPlacement extends SimplePlacement<NoPlacementConfig> {
	
	public OnlyEndIslandsPlacement() {
		super(NoPlacementConfig.CODEC);
	}
	
	@Override
	protected Stream<BlockPos> getPositions(Random random, NoPlacementConfig config, BlockPos pos) {
		if (pos.withinDistance(Vector3i.NULL_VECTOR, 500)) {
			return Stream.empty();
		}
		return Stream.of(pos);
	}
}
