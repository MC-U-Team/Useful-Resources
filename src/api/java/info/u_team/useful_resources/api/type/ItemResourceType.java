package info.u_team.useful_resources.api.type;

import java.util.*;

import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public enum ItemResourceType implements CacheResourceType<Item> {
	
	CRUSHED_ORE("crushed_ore"),
	NETHER_CRUSHED_ORE("nether_crushed_ore"),
	PURE_CRUSHED_ORE("pure_crushed_ore"),
	
	INGOT("ingot"),
	NUGGET("nugget"),
	
	GEM("gem"),
	PIECE("piece"),
	
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
	
	HELMET("helmet", "armors/helmets"),
	CHESTPLATE("chestplate", "armors/chestplates"),
	LEGGINGS("leggings", "armors/leggings"),
	BOOTS("boots", "armors/boots"),
	
	HORSE_ARMOR("horse_armor"),
	
	MOLTEN_BUCKET("molten_bucket");
	
	private static final Map<ResourceLocation, Tag<Item>> CACHE = new HashMap<>();
	
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
	public String getTagName() {
		return tagName;
	}
	
	@Override
	public Tag<Item> createTag(ResourceLocation location) {
		return TagUtil.createItemTag(location.getNamespace(), location.getPath());
	}
	
	@Override
	public Map<ResourceLocation, Tag<Item>> getCache() {
		return CACHE;
	}
	
}
