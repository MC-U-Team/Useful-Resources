package info.u_team.useful_resources.type;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.TriFunction;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceBlockConfig;
import info.u_team.useful_resources.api.resource.type.IResourceBlockType;
import info.u_team.useful_resources.block.ResourceBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraftforge.common.ToolType;

public enum ResourceBlockTypes implements IResourceBlockType {
	
	ORE("ore", Material.ROCK, SoundType.STONE, ToolType.PICKAXE),
	NETHER_ORE("nether_ore", Material.ROCK, SoundType.STONE, ToolType.PICKAXE),
	BLOCK("block", "storage_blocks", Material.IRON, SoundType.METAL, ToolType.PICKAXE);
	
	public static final List<ResourceBlockTypes> VALUES = Collections.unmodifiableList(Arrays.stream(values()).collect(Collectors.toList()));
	
	private final Map<String, Pair<Tag<Block>, Tag<Item>>> tagMap;
	
	private final String name;
	private final String tagName;
	
	private final Material material;
	private final SoundType soundType;
	private final ToolType harvestTool;
	
	private final TriFunction<IResource, IResourceBlockType, IResourceBlockConfig, Block> blockFunction;
	
	private final Tag<Block> unifyBlockTag;
	private final Tag<Item> unifyTag;
	
	private ResourceBlockTypes(String name, Material material, SoundType soundType, ToolType harvestTool) {
		this(name, material, soundType, harvestTool, ResourceBlock::new);
	}
	
	private ResourceBlockTypes(String name, Material material, SoundType soundType, ToolType harvestTool, TriFunction<IResource, IResourceBlockType, IResourceBlockConfig, Block> blockFunction) {
		this(name, name + "s", material, soundType, harvestTool, blockFunction);
	}
	
	private ResourceBlockTypes(String name, String tagName, Material material, SoundType soundType, ToolType harvestTool) {
		this(name, tagName, material, soundType, harvestTool, ResourceBlock::new);
	}
	
	private ResourceBlockTypes(String name, String tagName, Material material, SoundType soundType, ToolType harvestTool, TriFunction<IResource, IResourceBlockType, IResourceBlockConfig, Block> blockFunction) {
		tagMap = Maps.newHashMap();
		this.name = name;
		this.tagName = tagName;
		this.material = material;
		this.soundType = soundType;
		this.harvestTool = harvestTool;
		this.blockFunction = blockFunction;
		unifyBlockTag = TagUtil.createBlockTag("forge", tagName);
		unifyTag = TagUtil.fromBlockTag(unifyBlockTag);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Tag<Block> getUnifyBlockTag() {
		return unifyBlockTag;
	}
	
	@Override
	public Tag<Item> getUnifyTag() {
		return unifyTag;
	}
	
	@Override
	public Tag<Block> getBlockTag(IResourceBlocks blocks) {
		return getTagPair(blocks).getLeft();
	}
	
	@Override
	public Tag<Item> getTag(IResourceBlocks blocks) {
		return getTagPair(blocks).getRight();
	}
	
	private Pair<Tag<Block>, Tag<Item>> getTagPair(IResourceBlocks blocks) {
		if (blocks.hasBlock(this)) {
			return tagMap.computeIfAbsent(blocks.getResource().getName(), name -> {
				final Tag<Block> blockTag = TagUtil.createBlockTag("forge", tagName + "/" + name);
				return Pair.of(blockTag, TagUtil.fromBlockTag(blockTag));
			});
		}
		return Pair.of(null, null);
	}
	
	@Override
	public Block createBlock(IResource resource, IResourceBlockConfig config) {
		return blockFunction.apply(resource, this, config);
	}
	
	@Override
	public Material getMaterial() {
		return material;
	}
	
	@Override
	public SoundType getSoundType() {
		return soundType;
	}
	
	@Override
	public ToolType getHarvestTool() {
		return harvestTool;
	}
}
