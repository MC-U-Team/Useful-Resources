package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.forgeTags;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.data.resource.TagGenerationResources;

public class ResourceFluidTagsProvider extends CommonFluidTagsProvider {
	
	public ResourceFluidTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		TagGenerationResources.forEachFluid((resource, type, fluid) -> forgeTags(this::getBuilder, resource, type, fluid));
	}
}
