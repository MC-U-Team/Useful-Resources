package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.block.Block;

public interface IResourceBlocks extends IUArrayRegistryType<Block> {
	
	Block getOre();
	
	Block getNetherOre();
	
	Block getBlock();
	
	@Override
	default Block[] getArray() {
		return new Block[] { getOre(), getNetherOre(), getBlock() };
	}
	
}
