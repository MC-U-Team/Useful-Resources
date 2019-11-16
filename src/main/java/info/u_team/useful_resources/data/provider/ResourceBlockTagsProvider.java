package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.resource.IResourceBlocks;
import info.u_team.useful_resources.type.*;
import net.minecraft.block.Block;
import net.minecraft.tags.Tag;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		Resources.getValues().forEach(resource -> {
			final IResourceBlocks blocks = resource.getBlocks();
			ResourceBlockTypes.VALUES.stream().filter(blocks::hasBlock).forEach(type -> {
				final Tag<Block> tag = blocks.getBlockTag(type);
				getBuilder(tag).add(blocks.getBlock(type));
				getBuilder(blocks.getUnifyBlockTag(type)).add(tag);
			});
		});
	}
}
