package info.u_team.useful_resources.api.resource;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import info.u_team.useful_resources.api.resource.config.generation.IResourceGenerationConfig;
import info.u_team.useful_resources.api.resource.type.IResourceBlockType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public interface IResourceBlocks extends IUArrayRegistryType<Block> {
	
	IResource getResource();
	
	Block getBlock(IResourceBlockType type);
	
	boolean hasBlock(IResourceBlockType type);
	
	IResourceGenerationConfig getWorldGeneration(IResourceBlockType type);
	
	boolean hasWorldGeneration(IResourceBlockType type);
	
	default Tag<Block> getBlockTag(IResourceBlockType type) {
		return type.getBlockTag(this);
	}
	
	default Tag<Item> getTag(IResourceBlockType type) {
		return type.getTag(this);
	}
	
	default Tag<Block> getUnifyBlockTag(IResourceBlockType type) {
		return type.getUnifyBlockTag();
	}
	
	default Tag<Item> getUnifyTag(IResourceBlockType type) {
		return type.getUnifyTag();
	}
	
}
