package info.u_team.useful_resources.api.resource.type;

import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceBlockConfig;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.ToolType;

public interface IResourceBlockType extends IResourceType {
	
	Tag<Block> getUnifyBlockTag();
	
	Tag<Item> getUnifyTag();
	
	Tag<Block> getBlockTag(IResourceBlocks blocks);
	
	Tag<Item> getTag(IResourceBlocks blocks);
	
	Block createBlock(IResource resource, IResourceBlockConfig config);
	
	Material getMaterial();
	
	SoundType getSoundType();
	
	ToolType getHarvestTool();
	
}