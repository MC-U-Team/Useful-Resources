package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.useful_resources.api.IResourceBlocks;
import info.u_team.useful_resources.type.*;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(DataGenerator generator) {
		super("Resources-Block-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		registerBlocks();
	}
	
	private void registerBlocks() {
		Resources.VALUES.forEach(resource -> {
			final IResourceBlocks blocks = resource.getBlocks();
			ResourceBlockTypes.VALUES.forEach(type -> {
				final Tag<Block> tag = blocks.getBlockTag(type);
				getBuilder(tag).add(blocks.getBlock(type));
				getBuilder(blocks.getUnifyBlockTag(type)).add(tag);
			});
		});
	}
}
