package info.u_team.useful_resources.api.type;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

public enum FluidResourceType implements IResourceType<Fluid> {
	
	MOLTEN("molten", "molten"),
	MOLTEN_FLOWING("molten_flowing", "molten");
	
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
	public Tag<Fluid> getUnifyTag() {
		return new FluidTags.Wrapper(new ResourceLocation("forge", tagName));
	}
	
	@Override
	public Tag<Fluid> getTag(IResource resource) {
		return new FluidTags.Wrapper(new ResourceLocation("forge", tagName + "/" + resource.getName()));
	}
	
}
