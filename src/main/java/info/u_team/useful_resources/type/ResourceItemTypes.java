package info.u_team.useful_resources.type;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.Maps;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.item.ResourceItem;
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
	ROD("rod");
	
	public static final List<ResourceItemTypes> VALUES = Collections.unmodifiableList(Arrays.stream(values()).collect(Collectors.toList()));
	
	private final Map<String, Tag<Item>> tagMap;
	
	private final String name;
	private final String tagName;
	
	private final Tag<Item> unifyTag;
	
	private ResourceItemTypes(String name) {
		this(name, name + "s");
	}
	
	private ResourceItemTypes(String name, String tagName) {
		tagMap = Maps.newHashMap();
		this.name = name;
		this.tagName = tagName;
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
		return new ResourceItem(resource, this, config);
	}
}