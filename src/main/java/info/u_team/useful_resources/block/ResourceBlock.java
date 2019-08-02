package info.u_team.useful_resources.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceBlockConfig;
import info.u_team.useful_resources.api.resource.type.IResourceBlockType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class ResourceBlock extends UBlock {
	
	private final ToolType toolType;
	private final int harvestLevel;
	
	public ResourceBlock(IResource resource, IResourceBlockType type, IResourceBlockConfig config) {
		super(resource.getName() + "_" + type.getName(), UsefulResourcesItemGroups.GROUP, Block.Properties.create(type.getMaterial()).sound(type.getSoundType()).hardnessAndResistance(config.getHardness(), config.getResistance()), new Item.Properties().rarity(config.getRarity()));
		toolType = type.getHarvestTool();
		harvestLevel = config.getHarvestLevel();
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return toolType;
	}
	
	@Override
	public int getHarvestLevel(BlockState state) {
		return harvestLevel;
	}
	
}
