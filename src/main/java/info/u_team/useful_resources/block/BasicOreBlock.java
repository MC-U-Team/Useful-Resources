package info.u_team.useful_resources.block;

import java.util.Random;
import java.util.function.Function;

import info.u_team.useful_resources.api.block.IBlockRenderType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.common.ToolType;

public class BasicOreBlock extends ParticleBlock implements IBlockRenderType {
	
	private final Function<Random, Integer> experienceDrop;
	
	public BasicOreBlock(MaterialColor color, SoundType soundType, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		super(UsefulResourcesItemGroups.GROUP, Properties.create(Material.ROCK).sound(soundType).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).setRequiresTool().hardnessAndResistance(hardness, resistance), new Item.Properties().rarity(rarity));
		this.experienceDrop = experienceDrop;
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? experienceDrop.apply(RANDOM) : 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderType getType() {
		return BlockRenderType.CUTOUT_MIPPED;
	}
	
}
