package info.u_team.useful_resources.api.type;

import java.util.*;

import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public enum BlockResourceType implements CacheResourceType<Block> {
	
	ORE("ore", "stone_ores"),
	NETHER_ORE("nether_ore"),
	
	BLOCK("block", "storage_blocks"),
	
	MOLTEN_FLUID("molten_fluid", null);
	
	private static final Map<ResourceLocation, Tag<Block>> CACHE = new HashMap<>();
	
	private final String name;
	
	private final String tagName;
	
	private BlockResourceType(String name) {
		this(name, name + "s");
	}
	
	private BlockResourceType(String name, String tagName) {
		this.name = name;
		this.tagName = tagName;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getTagName() {
		return tagName;
	}
	
	@Override
	public Tag<Block> createTag(ResourceLocation location) {
		return TagUtil.createBlockTag(location.getNamespace(), location.getPath());
	}
	
	@Override
	public Map<ResourceLocation, Tag<Block>> getCache() {
		return CACHE;
	}
}
