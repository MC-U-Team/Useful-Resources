package info.u_team.useful_resources.block;

import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

public class BasicFenceBlock extends FenceBlock implements IBlockItemProvider, IParticleBlock {
	
	protected final BlockItem blockItem;
	
	public BasicFenceBlock(Rarity rarity, int harvestLevel, float hardness, float resistance) {
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
}
