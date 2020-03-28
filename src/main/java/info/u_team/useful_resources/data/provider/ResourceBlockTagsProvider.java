package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
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

		// Add vanilla ores to the right tags
		addBlockTag(BlockResourceType.ORE, Resources.IRON, Blocks.IRON_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.GOLD, Blocks.GOLD_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.DIAMOND, Blocks.DIAMOND_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.EMERALD, Blocks.EMERALD_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.LAPIS, Blocks.LAPIS_ORE);
		addBlockTag(BlockResourceType.NETHER_ORE, Resources.QUARTZ, Blocks.NETHER_QUARTZ_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.COAL, Blocks.COAL_ORE);
		
	}
	
	private void addBlockTag(BlockResourceType type, IResource resource, Block block) {
		final Tag<Block> tag = type.getTag(resource);
		getBuilder(tag).add(block);
		getBuilder(type.getUnifyTag()).add(tag);
	}
}
