package info.u_team.useful_resources.block;

import java.util.Random;
import java.util.function.Function;

import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

public class OreBlock extends ParticleBlock {
	
	private final Function<Random, Integer> experienceDrop;
	
	public OreBlock(String name, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		super(name, UsefulResourcesItemGroups.GROUP, Properties.create(Material.ROCK).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).hardnessAndResistance(hardness, resistance), new Item.Properties().rarity(rarity));
		this.experienceDrop = experienceDrop;
	}
	
	@Override
	public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
		return silktouch == 0 ? experienceDrop.apply(RANDOM) : 0;
	}
}
