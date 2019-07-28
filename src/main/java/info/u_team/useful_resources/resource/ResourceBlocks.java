package info.u_team.useful_resources.resource;

import java.util.Map;

import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.type.IResourceBlockType;
import info.u_team.useful_resources.type.ResourceBlockTypes;
import net.minecraft.block.Block;

public class ResourceBlocks implements IResourceBlocks {
	
	private final IResource resource;
	
	private final Map<ResourceBlockTypes, Block> blockMap;
	
	public ResourceBlocks(IResource resource, Map<ResourceBlockTypes, Block> blockMap) {
		this.resource = resource;
		this.blockMap = blockMap;
	}
	
	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public Block getBlock(IResourceBlockType type) {
		return blockMap.get(type);
	}
	
	@Override
	public boolean hasBlock(IResourceBlockType type) {
		return blockMap.containsKey(type);
	}
	
	@Override
	public Block[] getArray() {
		return blockMap.values().stream().toArray(Block[]::new);
	}
}
