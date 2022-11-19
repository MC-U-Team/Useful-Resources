package info.u_team.useful_resources.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.init.UsefulResourcesCreativeTabs;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BaseOreBlock extends UBlock {
	
	private final IntProvider xp;
	
	public BaseOreBlock(Rarity rarity, MaterialColor color, SoundType soundType, float destroyTime, float explosionResistance, IntProvider xp) {
		super(UsefulResourcesCreativeTabs.TAB, Properties.of(Material.STONE).color(color).sound(soundType).strength(destroyTime, explosionResistance).requiresCorrectToolForDrops(), new Item.Properties().rarity(rarity));
		this.xp = xp;
	}
	
	@Override
	public int getExpDrop(BlockState state, LevelReader level, RandomSource randomSource, BlockPos pos, int fortuneLevel, int silkTouchLevel) {
		return silkTouchLevel == 0 ? xp.sample(randomSource) : 0;
	}
	
}
