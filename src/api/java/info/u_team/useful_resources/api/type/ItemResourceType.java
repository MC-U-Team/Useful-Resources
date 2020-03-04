package info.u_team.useful_resources.api.type;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public enum ItemResourceType implements IResourceType {
	
	INGOT("ingot"),
	NUGGET("nugget"),
	DUST("dust"),
	PLATE("plate"),
	DENSE_PLATE("dense_plate"),
	GEAR("gear"),
	ROD("rod"),
	
	AXE("axe", "tools/axes"),
	HOE("hoe", "tools/hoes"),
	PICKAXE("pickaxe", "tools/pickaxes"),
	SHOVEL("shovel", "tools/shovels"),
	SWORD("sword", "tools/swords"),
	
	HELMET("helmet", "armor/helmets"),
	CHESTPLATE("chestplate", "armor/chestplates"),
	LEGGINGS("leggings", "armor/leggings"),
	BOOTS("boots", "armor/boots"),
	
	HORSE_ARMOR("horse_armor");
	
	private final String name;
	
	private final String tagName;
	
	private ItemResourceType(String name) {
		this(name, name + "s");
	}
	
	private ItemResourceType(String name, String tagName) {
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
	
}
