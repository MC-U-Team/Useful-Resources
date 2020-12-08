package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.forgeTags;

import java.util.Collection;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.*;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.data.resource.TagGenerationResources;
import info.u_team.useful_resources.data.util.TagGenerationUtil;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		TagGenerationResources.forEachBlock((resource, type, block) -> forgeTags(this::getBuilder, resource, type, block));
		
		TagGenerationResources.forEach(resource -> {
			// Add stone, nether and end ores to the ore tags
			TagGenerationUtil.addMoreCommonTag(CastUtil.uncheckedCast(resource.getBlocks()), this::getBuilder, TagUtil::createBlockTag, resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
		});
		
		// Special tags
		
		// Add vanilla blocks to the right tags
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
		addBlockTag(BlockResourceType.CHAIN, Resources.IRON, Blocks.CHAIN);
		addBlockTag(BlockResourceType.DOOR, Resources.IRON, Blocks.IRON_DOOR);
		addBlockTag(BlockResourceType.TRAPDOOR, Resources.IRON, Blocks.IRON_TRAPDOOR);
		
		// Add to vanilla tags
		
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.FENCE, BlockTags.FENCES);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.FENCE_GATE, BlockTags.FENCE_GATES);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.DOOR, BlockTags.DOORS);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.TRAPDOOR, BlockTags.TRAPDOORS);
	}
	
	private void addBlockTag(BlockResourceType type, IResource resource, Block block) {
		final INamedTag<Block> tag = type.getTag(resource);
		getBuilder(tag).add(block);
		getBuilder(type.getUnifyTag()).add(tag);
	}
	
	private void addToVanillaTag(Collection<IResource> resources, BlockResourceType type, INamedTag<Block> tag) {
		resources.stream() //
				.filter(resource -> resource.getBlocks().containsKey(type)) //
				.map(resource -> resource.getBlocks().get(type).get()) //
				.forEach(block -> getBuilder(tag).add(block));
	}
}
