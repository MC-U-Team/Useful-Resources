package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
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
			moreCommonTags(resource.getBlocks(), this::getBuilder, TagUtil::createBlockTag, resource, new ResourceLocation("forge", "ores"), ORE, NETHER_ORE, END_ORE);
		});
		
		// Add existing vanilla blocks to the right tags
		forgeTags(this::getBuilder, Resources.IRON, ORE, Blocks.IRON_ORE);
		forgeTags(this::getBuilder, Resources.GOLD, ORE, Blocks.GOLD_ORE);
		forgeTags(this::getBuilder, Resources.GOLD, NETHER_ORE, Blocks.NETHER_GOLD_ORE);
		forgeTags(this::getBuilder, Resources.DIAMOND, ORE, Blocks.DIAMOND_ORE);
		forgeTags(this::getBuilder, Resources.EMERALD, ORE, Blocks.EMERALD_ORE);
		forgeTags(this::getBuilder, Resources.LAPIS, ORE, Blocks.LAPIS_ORE);
		forgeTags(this::getBuilder, Resources.QUARTZ, NETHER_ORE, Blocks.NETHER_QUARTZ_ORE);
		forgeTags(this::getBuilder, Resources.COAL, ORE, Blocks.COAL_ORE);
		forgeTags(this::getBuilder, Resources.REDSTONE, ORE, Blocks.REDSTONE_ORE);
		forgeTags(this::getBuilder, Resources.IRON, BARS, Blocks.IRON_BARS);
		forgeTags(this::getBuilder, Resources.IRON, CHAIN, Blocks.CHAIN);
		forgeTags(this::getBuilder, Resources.IRON, DOOR, Blocks.IRON_DOOR);
		forgeTags(this::getBuilder, Resources.IRON, TRAPDOOR, Blocks.IRON_TRAPDOOR);
		
		// Add to the existing vanilla tags
		TagGenerationResources.forEach(resource -> {
			conditionTags(resource.getBlocks(), this::getBuilder, FENCE, BlockTags.FENCES);
			conditionTags(resource.getBlocks(), this::getBuilder, FENCE_GATE, BlockTags.FENCE_GATES);
			conditionTags(resource.getBlocks(), this::getBuilder, DOOR, BlockTags.DOORS);
			conditionTags(resource.getBlocks(), this::getBuilder, TRAPDOOR, BlockTags.TRAPDOORS);
		});
	}
}
