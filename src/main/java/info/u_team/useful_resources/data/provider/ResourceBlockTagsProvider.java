package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.block.Block;
import net.minecraft.tags.Tag;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().forEach((type, block) -> {
				final Tag<Block> tag = TagUtil.fromItemTag(type.getTag(resource));
				getBuilder(tag).add(block);
				getBuilder(TagUtil.fromItemTag(type.getUnifyTag())).add(tag);
			});
		});
	}
}
