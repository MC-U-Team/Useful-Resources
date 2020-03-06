package info.u_team.useful_resources.data.provider;

import java.util.Optional;
import java.util.Map.Entry;

import info.u_team.move_to_u_team_core.CommonFluidTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.Tag;
import net.minecraft.tags.Tag.Builder;

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
	
	@Override
	protected Builder<Fluid> getBuilder(Tag<Fluid> tag) {
		final Optional<Builder<Fluid>> optional = tagToBuilder.entrySet().stream().filter(entry -> entry.getKey().getId().equals(tag.getId())).findAny().map(Entry::getValue);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return super.getBuilder(tag);
		}
	}
}
