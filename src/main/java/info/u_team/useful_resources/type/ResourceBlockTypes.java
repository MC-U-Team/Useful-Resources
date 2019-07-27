package info.u_team.useful_resources.type;

import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.block.ResourceBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public enum ResourceBlockTypes implements IResourceBlockTypes {
	
	ORE("ore"),
	NETHER_ORE("nether_ore"),
	BLOCK("block", "storage_blocks");
	
	public static final List<ResourceBlockTypes> VALUES = Collections.unmodifiableList(Arrays.stream(values()).collect(Collectors.toList()));
	
	private final Map<String, Pair<Tag<Block>, Tag<Item>>> tagMap;
	
	private final String name;
	private final String tagName;
	
	private final Tag<Block> unifyBlockTag;
	private final Tag<Item> unifyTag;
	
	private ResourceBlockTypes(String name) {
		this(name, name + "s");
	}
	
	private ResourceBlockTypes(String name, String tagName) {
		tagMap = Maps.newHashMap();
		this.name = name;
		this.tagName = tagName;
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
	public Block createBlock(IResource resource) {
		return new ResourceBlock(name, resource, Block.Properties.create(Material.SNOW), () -> 1F, () -> 1F);
	}
	
}
