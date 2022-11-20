package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.data.util.GenerationResourceRegistry;
import info.u_team.useful_resources.data.util.TagGenerationUtil;

public class UsefulResourcesItemTagsProvider extends CommonItemTagsProvider {
	
	public UsefulResourcesItemTagsProvider(GenerationData generationData, CommonBlockTagsProvider blockTagsProvider) {
		super(generationData, blockTagsProvider);
	}
	
	@Override
	public void register() {
		GenerationResourceRegistry.forEachItem((resource, type, item) -> TagGenerationUtil.forgeTags(this::tag, resource, type, item));
		GenerationResourceRegistry.forEachBlock((resource, type, block) -> TagGenerationUtil.forgeTagsCopy(this::copy, resource, type));
	}
}
