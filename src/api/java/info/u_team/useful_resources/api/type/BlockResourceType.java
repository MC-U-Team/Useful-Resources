package info.u_team.useful_resources.api.type;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public enum BlockResourceType implements IResourceType {
	
	ORE("ore"),
	NETHER_ORE("nether_ore"),
	BLOCK("block", "storage_blocks");
	
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
	public Tag<Item> getUnifyTag() {
		return TagUtil.createItemTag("forge", tagName);
	}
	
	@Override
	public Tag<Item> getTag(IResource resource) {
		return TagUtil.createItemTag("forge", tagName + "/" + resource.getName());
	}
	
	public Tag<Block> getBlockUnifyTag() {
		return TagUtil.fromItemTag(getUnifyTag());
	}
	
	public Tag<Block> getBlockTag(IResource resource) {
		return TagUtil.fromItemTag(getTag(resource));
	}
	
}
