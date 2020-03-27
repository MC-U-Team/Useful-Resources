package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.block.*;
import net.minecraft.tags.Tag;
import net.minecraft.tags.Tag.Builder;
import net.minecraftforge.common.Tags;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				if (type.hasTag()) {
					final Tag<Block> tag = type.getTag(resource);
					getBuilder(tag).add(block);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
			final boolean hasOre = resource.getBlocks().containsKey(BlockResourceType.ORE);
			final boolean hasNetherOre = resource.getBlocks().containsKey(BlockResourceType.NETHER_ORE);
			if (hasOre || hasNetherOre) {
				final Tag<Block> tag = TagUtil.createBlockTag("forge", "ores/" + resource.getName());
				final Builder<Block> builder = getBuilder(tag);
				if (hasOre) {
					builder.add(BlockResourceType.ORE.getTag(resource));
				}
				if (hasNetherOre) {
					builder.add(BlockResourceType.NETHER_ORE.getTag(resource));
				}
				getBuilder(Tags.Blocks.ORES).add(tag);
			}
		});
		
		// Special tags
		
		// Add nether quartz ore to the nether ores tag
		final Tag<Block> netherQuartzOreTag = BlockResourceType.NETHER_ORE.getTag(Resources.QUARTZ);
		getBuilder(netherQuartzOreTag).add(Blocks.NETHER_QUARTZ_ORE);
		getBuilder(BlockResourceType.NETHER_ORE.getUnifyTag()).add(netherQuartzOreTag);
	}
}
