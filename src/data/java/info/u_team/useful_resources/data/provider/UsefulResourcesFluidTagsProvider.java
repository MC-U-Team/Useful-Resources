package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonFluidTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.data.util.GenerationResourceRegistry;
import info.u_team.useful_resources.data.util.TagGenerationUtil;

public class UsefulResourcesFluidTagsProvider extends CommonFluidTagsProvider {
	
	public UsefulResourcesFluidTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		GenerationResourceRegistry.forEachFluid((resource, type, block) -> TagGenerationUtil.forgeTags(this::tag, resource, type, block));
	}
}
