package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.data.util.GenerationResourceRegistry;
import info.u_team.useful_resources.data.util.TagGenerationUtil;

public class UsefulResourcesBlockTagsProvider extends CommonBlockTagsProvider {
	
	public UsefulResourcesBlockTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		GenerationResourceRegistry.forEachBlock((resource, type, block) -> TagGenerationUtil.forgeTags(this::tag, resource, type, block));
	}
}
