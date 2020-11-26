package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.resources.Resources;
import info.u_team.useful_resources.util.FakeNameResource;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.ITag.INamedTag;

public class ResourceFluidTagsProvider extends CommonFluidTagsProvider {
	
	public ResourceFluidTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryFluids((type, fluid) -> {
				if (type.hasTag()) {
					final INamedTag<Fluid> tag = type.getTag(resource);
					getBuilder(tag).add(fluid);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
		});
		
		// Special tags
		
		// Add aluminum to the aluminium tag
		final FakeNameResource aluminium = new FakeNameResource("aluminium", Resources.ALUMINUM);
		aluminium.iterateRegistryFluids((type, fluid) -> {
			if (type.hasTag()) {
				final INamedTag<Fluid> tag = type.getTag(aluminium);
				getBuilder(tag).add(fluid);
				if (type.hasUnifyTag()) {
					getBuilder(type.getUnifyTag()).add(tag);
				}
			}
		});
	}
}
