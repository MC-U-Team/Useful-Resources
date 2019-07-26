package info.u_team.useful_resources.resource;

import java.util.EnumMap;

import com.google.common.collect.Maps;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.BlockResourceTypes;
import net.minecraft.block.Block;

public class BlockResourceSet implements IResourceBlocks {
	
	private final IResource resource;
	
	private final EnumMap<BlockResourceTypes, Block> blockMap;
	
	public BlockResourceSet(IResource resource) {
		this.resource = resource;
		blockMap = Maps.newEnumMap(BlockResourceTypes.class);
		BlockResourceTypes.VALUES.forEach(type -> blockMap.put(type, type.createBlock(resource)));
	}
	
	@Override
	public IResource getResource() {
		return resource;
	}
	
	@Override
	public Block getBlock(IBlockResourceTypes type) {
		return blockMap.get(type);
	}
	
	@Override
	public boolean hasBlock(IBlockResourceTypes type) {
		return blockMap.containsKey(type);
	}
	
	@Override
	public Block[] getArray() {
		return blockMap.values().stream().toArray(Block[]::new);
	}
}
