package info.u_team.useful_resources.block;

import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class BasicFenceGateBlock extends FenceGateBlock implements IBlockItemProvider, IParticleBlock {
	
	protected final BlockItem blockItem;
	
	public BasicFenceGateBlock(Rarity rarity, int harvestLevel, float hardness, float resistance) {
		super(Properties.create(Material.IRON).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).setRequiresTool().hardnessAndResistance(hardness, resistance));
		blockItem = createBlockItem(new Item.Properties().group(UsefulResourcesItemGroups.GROUP).rarity(rarity));
	}
	
	protected BlockItem createBlockItem(Item.Properties blockItemProperties) {
		return new BlockItem(this, blockItemProperties);
	}
	
	@Override
	public BlockItem getBlockItem() {
		return blockItem;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (state.get(OPEN)) {
			state = state.with(OPEN, false);
			world.setBlockState(pos, state, 10);
		} else {
			final Direction direction = player.getHorizontalFacing();
			if (state.get(HORIZONTAL_FACING) == direction.getOpposite()) {
				state = state.with(HORIZONTAL_FACING, direction);
			}
			state = state.with(OPEN, true);
			world.setBlockState(pos, state, 10);
		}
		world.playEvent(player, state.get(OPEN) ? 1037 : 1036, pos, 0);
		return ActionResultType.func_233537_a_(world.isRemote);
	}
	
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
		if (!world.isRemote) {
			final boolean powered = world.isBlockPowered(pos);
			if (state.get(POWERED) != powered) {
				world.setBlockState(pos, state.with(POWERED, powered).with(OPEN, powered), 2);
				if (state.get(OPEN) != powered) {
					world.playEvent(null, powered ? 1037 : 1036, pos, 0);
				}
			}
		}
	}
}
