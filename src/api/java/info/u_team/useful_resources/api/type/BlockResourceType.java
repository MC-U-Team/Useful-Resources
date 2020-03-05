package info.u_team.useful_resources.api.type;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.block.Block;
import net.minecraft.tags.Tag;

public enum BlockResourceType implements IResourceType<Block> {
	
	ORE("ore"),
	NETHER_ORE("nether_ore"),
	
	BLOCK("block", "storage_blocks"),
	
	MOLTEN_BLOCK("molten_block", "molten_blocks");
	
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
	public Tag<Block> getUnifyTag() {
		return TagUtil.createBlockTag("forge", tagName);
	}
	
	@Override
	public Tag<Block> getTag(IResource resource) {
		return TagUtil.createBlockTag("forge", tagName + "/" + resource.getName());
	}
}
