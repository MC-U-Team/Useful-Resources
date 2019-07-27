package info.u_team.useful_resources.api;

import info.u_team.useful_resources.api.config.IResourceBlockConfig;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.ToolType;

public interface IResourceBlockType {
	
	String getName();
	
	Tag<Block> getUnifyBlockTag();
	
	Tag<Item> getUnifyTag();
	
	Tag<Block> getBlockTag(IResourceBlocks blocks);
	
	Tag<Item> getTag(IResourceBlocks blocks);
	
	Block createBlock(IResource resource, IResourceBlockConfig config);
	
	Material getMaterial();
	
	SoundType getSoundType();
	
	ToolType getHarvestTool();
	
}