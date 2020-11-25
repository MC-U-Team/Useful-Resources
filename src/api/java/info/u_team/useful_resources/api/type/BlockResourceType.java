package info.u_team.useful_resources.api.type;

import java.util.*;

import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.block.Block;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public enum BlockResourceType implements CacheResourceType<Block> {
	
	ORE("ore", "stone_ores"),
	NETHER_ORE("nether_ore"),
	END_ORE("end_ore"),
	
	BLOCK("block", "storage_blocks"),
	
	MOLTEN_FLUID("molten_fluid", null);
	
	private static final Map<ResourceLocation, INamedTag<Block>> CACHE = new HashMap<>();
	
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
	public INamedTag<Block> createTag(ResourceLocation location) {
		return TagUtil.createBlockTag(location);
	}
	
	@Override
	public Map<ResourceLocation, INamedTag<Block>> getCache() {
		return CACHE;
	}
}
