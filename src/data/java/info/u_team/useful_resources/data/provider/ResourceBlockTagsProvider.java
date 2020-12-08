package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import java.util.Collection;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.data.resource.TagGenerationResources;
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
			moreCommonTags(resource.getBlocks(), this::getBuilder, TagUtil::createBlockTag, resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
		});
		
		// Special tags
		
		// Add vanilla blocks to the right tags
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
		
		// Add to vanilla tags
		
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.FENCE, BlockTags.FENCES);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.FENCE_GATE, BlockTags.FENCE_GATES);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.DOOR, BlockTags.DOORS);
		addToVanillaTag(TagGenerationResources.getResources(), BlockResourceType.TRAPDOOR, BlockTags.TRAPDOORS);
	}
	
	private void addToVanillaTag(Collection<IResource> resources, BlockResourceType type, INamedTag<Block> tag) {
		resources.stream() //
				.filter(resource -> resource.getBlocks().containsKey(type)) //
				.map(resource -> resource.getBlocks().get(type).get()) //
				.forEach(block -> getBuilder(tag).add(block));
	}
}
