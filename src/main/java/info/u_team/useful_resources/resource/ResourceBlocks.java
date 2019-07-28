package info.u_team.useful_resources.resource;

import java.util.Map;

import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceGenerationConfig;
import info.u_team.useful_resources.api.resource.type.IResourceBlockType;
import info.u_team.useful_resources.config.ResourceGenerationConfig;
import info.u_team.useful_resources.type.ResourceBlockTypes;
import net.minecraft.block.Block;

public class ResourceBlocks implements IResourceBlocks {
	
	private final IResource resource;
	
	private final Map<ResourceBlockTypes, Block> blockMap;
	private final Map<ResourceBlockTypes, ResourceGenerationConfig> generationConfig;
	
	public ResourceBlocks(IResource resource, Map<ResourceBlockTypes, Block> blockMap, Map<ResourceBlockTypes, ResourceGenerationConfig> generationConfig) {
		this.resource = resource;
		this.blockMap = blockMap;
		this.generationConfig = generationConfig;
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
	public IResourceGenerationConfig getWorldGeneration(IResourceBlockType type) {
		return generationConfig.get(type);
	}
	
	@Override
	public boolean hasWorldGeneration(IResourceBlockType type) {
		return generationConfig.containsKey(type);
	}
	
	@Override
	public Block[] getArray() {
		return blockMap.values().stream().toArray(Block[]::new);
	}
}
