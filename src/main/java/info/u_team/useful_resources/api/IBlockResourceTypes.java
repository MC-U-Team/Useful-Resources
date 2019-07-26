package info.u_team.useful_resources.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IBlockResourceTypes {
	
	String getName();
	
	Tag<Block> getUnifyBlockTag();
	
	Tag<Item> getUnifyTag();
	
	Tag<Block> getBlockTag(IResourceBlocks blocks);
	
	Tag<Item> getTag(IResourceBlocks blocks);
	
	Block createBlock(IResource resource);
	
}