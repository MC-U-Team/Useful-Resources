package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.data.resource.TagGenerationResources;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.item.Items;
import net.minecraft.tags.*;
import net.minecraft.util.ResourceLocation;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data, CommonBlockTagsProvider blockProvider) {
		super(data, blockProvider);
	}
	
	@Override
	protected void registerTags() {
		TagGenerationResources.forEachBlock((resource, type, block) -> forgeTagsCopy(this::copy, resource, type));
		TagGenerationResources.forEachItem((resource, type, item) -> forgeTags(this::getBuilder, resource, type, item));
		
		TagGenerationResources.forEach(resource -> {
			// Add stone, nether and end ores to the ore tags
			moreCommonTagsCopy(this::copy, resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
			
			// Add crushed stone, nether and end ores to the crushed ore tags
			moreCommonTags(resource.getItems(), this::getBuilder, TagUtil::createItemTag, resource, new ResourceLocation("forge", "crushed_ores"), ItemResourceType.CRUSHED_ORE, ItemResourceType.CRUSHED_NETHER_ORE, ItemResourceType.CRUSHED_END_ORE);
		});
		
		moreCommonUnifyTags(this::getBuilder, TagUtil::createItemTag, new ResourceLocation("forge", "tools"), ItemResourceType.AXE, ItemResourceType.HOE, ItemResourceType.PICKAXE, ItemResourceType.SHOVEL, ItemResourceType.SWORD);
		moreCommonUnifyTags(this::getBuilder, TagUtil::createItemTag, new ResourceLocation("forge", "armors"), ItemResourceType.HELMET, ItemResourceType.CHESTPLATE, ItemResourceType.LEGGINGS, ItemResourceType.BOOTS);
		
		// Add vanilla blocks to the right tags
		forgeTagsCopy(this::copy, Resources.IRON, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.GOLD, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.GOLD, BlockResourceType.NETHER_ORE);
		forgeTagsCopy(this::copy, Resources.DIAMOND, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.EMERALD, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.LAPIS, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.QUARTZ, BlockResourceType.NETHER_ORE);
		forgeTagsCopy(this::copy, Resources.COAL, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.REDSTONE, BlockResourceType.ORE);
		forgeTagsCopy(this::copy, Resources.IRON, BlockResourceType.BARS);
		forgeTagsCopy(this::copy, Resources.IRON, BlockResourceType.CHAIN);
		forgeTagsCopy(this::copy, Resources.IRON, BlockResourceType.DOOR);
		forgeTagsCopy(this::copy, Resources.IRON, BlockResourceType.TRAPDOOR);
		
		// Add vanilla items to the right tags
		forgeTags(this::getBuilder, Resources.COAL, ItemResourceType.GEM, Items.COAL);
		
		// Add to vanilla tags
		copy(BlockTags.FENCES, ItemTags.FENCES);
		copy(BlockTags.DOORS, ItemTags.DOORS);
		copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
	}
}
