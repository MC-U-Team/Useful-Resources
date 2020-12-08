package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.data.resource.TagGenerationResources;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		// Generate the basic forge and forge unify tags
		TagGenerationResources.forEachBlock((resource, type, block) -> forgeTags(this::getBuilder, resource, type, block));
		
		// Generate more common tags
		TagGenerationResources.forEach(resource -> {
			moreCommonTags(resource.getBlocks(), this::getBuilder, TagUtil::createBlockTag, resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
		});
		
		// Add existing vanilla blocks to the right tags
		forgeTags(this::getBuilder, Resources.IRON, BlockResourceType.ORE, Blocks.IRON_ORE);
		forgeTags(this::getBuilder, Resources.GOLD, BlockResourceType.ORE, Blocks.GOLD_ORE);
		forgeTags(this::getBuilder, Resources.GOLD, BlockResourceType.NETHER_ORE, Blocks.NETHER_GOLD_ORE);
		forgeTags(this::getBuilder, Resources.DIAMOND, BlockResourceType.ORE, Blocks.DIAMOND_ORE);
		forgeTags(this::getBuilder, Resources.EMERALD, BlockResourceType.ORE, Blocks.EMERALD_ORE);
		forgeTags(this::getBuilder, Resources.LAPIS, BlockResourceType.ORE, Blocks.LAPIS_ORE);
		forgeTags(this::getBuilder, Resources.QUARTZ, BlockResourceType.NETHER_ORE, Blocks.NETHER_QUARTZ_ORE);
		forgeTags(this::getBuilder, Resources.COAL, BlockResourceType.ORE, Blocks.COAL_ORE);
		forgeTags(this::getBuilder, Resources.REDSTONE, BlockResourceType.ORE, Blocks.REDSTONE_ORE);
		forgeTags(this::getBuilder, Resources.IRON, BlockResourceType.BARS, Blocks.IRON_BARS);
		forgeTags(this::getBuilder, Resources.IRON, BlockResourceType.CHAIN, Blocks.CHAIN);
		forgeTags(this::getBuilder, Resources.IRON, BlockResourceType.DOOR, Blocks.IRON_DOOR);
		forgeTags(this::getBuilder, Resources.IRON, BlockResourceType.TRAPDOOR, Blocks.IRON_TRAPDOOR);
		
		// Add to the existing vanilla tags
		TagGenerationResources.forEach(resource -> {
			conditionTags(resource.getBlocks(), this::getBuilder, BlockResourceType.FENCE, BlockTags.FENCES);
			conditionTags(resource.getBlocks(), this::getBuilder, BlockResourceType.FENCE_GATE, BlockTags.FENCE_GATES);
			conditionTags(resource.getBlocks(), this::getBuilder, BlockResourceType.DOOR, BlockTags.DOORS);
			conditionTags(resource.getBlocks(), this::getBuilder, BlockResourceType.TRAPDOOR, BlockTags.TRAPDOORS);
		});
	}
}
