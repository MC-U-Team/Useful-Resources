package info.u_team.useful_resources.api.type;

import info.u_team.useful_resources.api.resource.IResource;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.Tag;

public enum FluidResourceType implements IResourceType<Fluid> {
	;
	
	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public Tag<Fluid> getUnifyTag() {
		return null;
	}
	
	@Override
	public Tag<Fluid> getTag(IResource resource) {
		return null;
	}
	
}
