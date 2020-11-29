package info.u_team.useful_resources.block;

import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.useful_resources.api.block.IBlockRenderType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

public class BasicBarsBlock extends PaneBlock implements IBlockItemProvider, IParticleBlock, IBlockRenderType {
	
	protected final BlockItem blockItem;
	
	public BasicBarsBlock(Rarity rarity, int harvestLevel, float hardness, float resistance) {
		super(Properties.create(Material.IRON, MaterialColor.AIR).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).setRequiresTool().hardnessAndResistance(hardness, resistance).notSolid());
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
	public BlockRenderType getType() {
		return BlockRenderType.CUTOUT_MIPPED;
	}
	
}
