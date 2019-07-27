package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceBlocks extends IUArrayRegistryType<Block> {
	
	IResource getResource();
	
	Block getBlock(IResourceBlockTypes type);
	
	boolean hasBlock(IResourceBlockTypes type);
	
	default Tag<Block> getBlockTag(IResourceBlockTypes type) {
		return type.getBlockTag(this);
	}
	
	default Tag<Item> getTag(IResourceBlockTypes type) {
		return type.getTag(this);
	}
	
	default Tag<Block> getUnifyBlockTag(IResourceBlockTypes type) {
		return type.getUnifyBlockTag();
	}
	
	default Tag<Item> getUnifyTag(IResourceBlockTypes type) {
		return type.getUnifyTag();
	}
	
}
