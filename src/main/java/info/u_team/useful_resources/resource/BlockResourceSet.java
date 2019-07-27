package info.u_team.useful_resources.resource;

import java.util.EnumMap;

import com.google.common.collect.Maps;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.api.config.IResourceBlockConfig;
import info.u_team.useful_resources.type.ResourceBlockTypes;
import net.minecraft.block.Block;
import net.minecraft.item.Rarity;

public class BlockResourceSet implements IResourceBlocks {
	
	private final IResource resource;
	
	private final EnumMap<ResourceBlockTypes, Block> blockMap;
	
	public BlockResourceSet(IResource resource) {
		this.resource = resource;
		blockMap = Maps.newEnumMap(ResourceBlockTypes.class);
		ResourceBlockTypes.VALUES.forEach(type -> blockMap.put(type, type.createBlock(resource, new IResourceBlockConfig() { // Just default for testing immo
			
			@Override
			public Rarity getRarity() {
				return Rarity.COMMON;
			}
			
			@Override
			public float getResistance() {
				return 5;
			}
			
			@Override
			public int getHarvestLevel() {
				return 1;
			}
			
			@Override
			public float getHardness() {
				return 2;
			}
		})));
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
