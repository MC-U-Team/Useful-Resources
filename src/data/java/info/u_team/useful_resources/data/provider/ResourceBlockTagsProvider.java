package info.u_team.useful_resources.data.provider;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.data.TagGenerationResources;
import info.u_team.useful_resources.resources.Resources;
import info.u_team.useful_resources.util.MoreCollectors;
import net.minecraft.block.*;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		TagGenerationResources.forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				if (type.hasTag()) {
					final INamedTag<Block> tag = type.getTag(resource);
					getBuilder(tag).add(block);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
			
			// Add stone, nether and end ores to the ore tags
			addMoreCommonTag(resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
		});
		
		// Special tags
		
		// Add vanilla ores to the right tags
		addBlockTag(BlockResourceType.ORE, Resources.IRON, Blocks.IRON_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.GOLD, Blocks.GOLD_ORE);
		addBlockTag(BlockResourceType.NETHER_ORE, Resources.GOLD, Blocks.NETHER_GOLD_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.DIAMOND, Blocks.DIAMOND_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.EMERALD, Blocks.EMERALD_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.LAPIS, Blocks.LAPIS_ORE);
		addBlockTag(BlockResourceType.NETHER_ORE, Resources.QUARTZ, Blocks.NETHER_QUARTZ_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.COAL, Blocks.COAL_ORE);
		addBlockTag(BlockResourceType.ORE, Resources.REDSTONE, Blocks.REDSTONE_ORE);
		addBlockTag(BlockResourceType.BARS, Resources.IRON, Blocks.IRON_BARS);
	}
	
	private void addMoreCommonTag(IResource resource, ResourceLocation baseTag, BlockResourceType... types) {
		final Map<BlockResourceType, Boolean> hasType = Stream.of(types).collect(MoreCollectors.toLinkedMap(Function.identity(), type -> resource.getBlocks().containsKey(type)));
		if (hasType.containsValue(true)) {
			final INamedTag<Block> tag = TagUtil.createBlockTag(baseTag.getNamespace(), baseTag.getPath() + "/" + resource.getName());
			final BetterBuilder<Block> builder = getBuilder(tag);
			hasType.entrySet().stream().filter(entry -> entry.getValue().equals(true)).map(Entry::getKey).forEach(type -> {
				builder.add(type.getTag(resource));
			});
			getBuilder(TagUtil.createBlockTag(baseTag)).add(tag);
		}
	}
	
	private void addBlockTag(BlockResourceType type, IResource resource, Block block) {
		final INamedTag<Block> tag = type.getTag(resource);
		getBuilder(tag).add(block);
		getBuilder(type.getUnifyTag()).add(tag);
	}
}
