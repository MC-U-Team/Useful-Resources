package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.Tag;

public class ResourceFluidTagsProvider extends CommonFluidTagsProvider {
	
	public ResourceFluidTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getFluids().forEach((type, fluid) -> {
				if (type.hasTag()) {
					final Tag<Fluid> tag = type.getTag(resource);
					getBuilder(tag).add(fluid);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
		});
	}
}
