package info.u_team.useful_resources.api.type;

import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public enum BlockResourceType implements IResourceType {
	
	ORE("ore"),
	NETHER_ORE("nether_ore"),
	BLOCK("block", "storage_blocks");
	
	private final String name;
	
	private final Tag<Item> unifyTag;
	
	private BlockResourceType(String name) {
		this(name, name + "s");
	}
	
	private BlockResourceType(String name, String unifyTagName) {
		this.name = name;
		unifyTag = TagUtil.createItemTag("forge", unifyTagName);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public Tag<Item> getUnifyTag() {
		return unifyTag;
	}
	
}
