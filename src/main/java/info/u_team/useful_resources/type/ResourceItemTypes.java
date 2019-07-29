package info.u_team.useful_resources.type;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.TriFunction;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.item.*;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraft.util.IStringSerializable;

public enum ResourceItemTypes implements IStringSerializable, IResourceItemType {
	
	INGOT("ingot"),
	NUGGET("nugget"),
	DUST("dust"),
	PLATE("plate"),
	DENSE_PLATE("dense_plate"),
	GEAR("gear"),
	ROD("rod"),
	
	AXE("axe", ResourceAxeItem::new),
	HOE("hoe", ResourceHoeItem::new),
	PICKAXE("pickaxe", ResourcePickaxeItem::new),
	SPADE("shovel", ResourceSpadeItem::new),
	SWORD("sword", ResourceSwordItem::new),
	
	HELMET("helmet", ResourceHelmetItem::new),
	CHESTPLATE("chestplate", ResourceChestplateItem::new),
	LEGGINGS("leggings", ResourceLeggingsItem::new),
	BOOTS("boots", ResourceBootsItem::new);
	
	public static final List<ResourceItemTypes> VALUES = Collections.unmodifiableList(Arrays.stream(values()).collect(Collectors.toList()));
	
	private final Map<String, Tag<Item>> tagMap;
	
	private final String name;
	private final String tagName;
	
	private final TriFunction<IResource, IResourceItemType, IResourceItemConfig, Item> itemFunction;
	
	private final Tag<Item> unifyTag;
	
	private ResourceItemTypes(String name) {
		this(name, ResourceItem::new);
	}
	
	private ResourceItemTypes(String name, TriFunction<IResource, IResourceItemType, IResourceItemConfig, Item> itemFunction) {
		this(name, name + "s", itemFunction);
	}
	
	private ResourceItemTypes(String name, String tagName) {
		this(name, tagName, ResourceItem::new);
	}
	
	private ResourceItemTypes(String name, String tagName, TriFunction<IResource, IResourceItemType, IResourceItemConfig, Item> itemFunction) {
		tagMap = Maps.newHashMap();
		this.name = name;
		this.tagName = tagName;
		this.itemFunction = itemFunction;
		unifyTag = TagUtil.createItemTag("forge", tagName);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Tag<Item> getUnifyTag() {
		return unifyTag;
	}
	
	@Override
	public Tag<Item> getTag(IResourceItems items) {
		if (items.hasItem(this)) {
			return tagMap.computeIfAbsent(items.getResource().getName(), name -> {
				return TagUtil.createItemTag("forge", tagName + "/" + name);
			});
		}
		return null;
	}
	
	@Override
	public Item createItem(IResource resource, IResourceItemConfig config) {
		return itemFunction.apply(resource, this, config);
	}
}
