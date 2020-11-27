package info.u_team.useful_resources.api.type;

import java.util.*;

import info.u_team.u_team_core.util.TagUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public enum FluidResourceType implements CacheResourceType<Fluid> {
	
	MOLTEN("molten"),
	MOLTEN_FLOWING("molten_flowing", "moltens");
	
	private static final Map<ResourceLocation, INamedTag<Fluid>> CACHE = new HashMap<>();
	
	private final String name;
	
	private final String tagName;
	
	private FluidResourceType(String name) {
		this(name, name + "s");
	}
	
	private FluidResourceType(String name, String tagName) {
		this.name = name;
		this.tagName = tagName;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getTagName() {
		return tagName;
	}
	
	@Override
	public INamedTag<Fluid> createTag(ResourceLocation location) {
		return TagUtil.createFluidTag(location);
	}
	
	@Override
	public Map<ResourceLocation, INamedTag<Fluid>> getCache() {
		return CACHE;
	}
	
}
