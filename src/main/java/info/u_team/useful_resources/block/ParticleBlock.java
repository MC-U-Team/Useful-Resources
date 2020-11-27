package info.u_team.useful_resources.block;

import info.u_team.u_team_core.block.UBlock;
import net.minecraft.item.ItemGroup;

public abstract class ParticleBlock extends UBlock implements ParticleIForgeBlock {
	
	public ParticleBlock(ItemGroup group, Properties properties, net.minecraft.item.Item.Properties blockItemProperties) {
		super(group, properties, blockItemProperties);
	}
}
