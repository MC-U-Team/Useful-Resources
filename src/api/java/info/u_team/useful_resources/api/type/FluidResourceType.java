package info.u_team.useful_resources.api.type;

import java.util.*;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

public enum FluidResourceType implements CacheResourceType<Fluid> {
	
	MOLTEN("molten"),
	MOLTEN_FLOWING("molten_flowing", "moltens");
	
	private static final Map<ResourceLocation, Tag<Fluid>> CACHE = new HashMap<>();
	
	private final String name;
	
	private final String tagName;
	
	private FluidResourceType(String name) {
		this(name, name + "s");
	}
	
	private FluidResourceType(String name, String tagName) {
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
	public Tag<Fluid> createTag(ResourceLocation location) {
		return new FluidTags.Wrapper(location);
	}
	
	@Override
	public Map<ResourceLocation, Tag<Fluid>> getCache() {
		return CACHE;
	}
	
}
