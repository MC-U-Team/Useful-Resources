package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
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
		// Generate the basic forge and forge unify tags
		TagGenerationResources.forEachBlock((resource, type, block) -> forgeTagsCopy(this::copy, resource, type));
		TagGenerationResources.forEachItem((resource, type, item) -> forgeTags(this::getBuilder, resource, type, item));
		
		// Generate more common tags
		TagGenerationResources.forEach(resource -> {
			moreCommonTagsCopy(this::copy, resource, new ResourceLocation("forge", "ores"), ORE, NETHER_ORE, END_ORE);
			moreCommonTags(this::getBuilder, resource.getItems(), TagUtil::createItemTag, resource, new ResourceLocation("forge", "crushed_ores"), CRUSHED_ORE, CRUSHED_NETHER_ORE, CRUSHED_END_ORE);
		});
		
		// Generate more common unify tags
		moreCommonUnifyTags(this::getBuilder, TagUtil::createItemTag, new ResourceLocation("forge", "tools"), AXE, HOE, PICKAXE, SHOVEL, SWORD);
		moreCommonUnifyTags(this::getBuilder, TagUtil::createItemTag, new ResourceLocation("forge", "armors"), HELMET, CHESTPLATE, LEGGINGS, BOOTS);
		
		// Add existing vanilla blocks / items to the right tags
		forgeTagsCopy(this::copy, Resources.IRON, ORE);
		forgeTagsCopy(this::copy, Resources.GOLD, ORE);
		forgeTagsCopy(this::copy, Resources.GOLD, NETHER_ORE);
		forgeTagsCopy(this::copy, Resources.DIAMOND, ORE);
		forgeTagsCopy(this::copy, Resources.EMERALD, ORE);
		forgeTagsCopy(this::copy, Resources.LAPIS, ORE);
		forgeTagsCopy(this::copy, Resources.QUARTZ, NETHER_ORE);
		forgeTagsCopy(this::copy, Resources.COAL, ORE);
		forgeTagsCopy(this::copy, Resources.REDSTONE, ORE);
		forgeTagsCopy(this::copy, Resources.IRON, BARS);
		forgeTagsCopy(this::copy, Resources.IRON, CHAIN);
		forgeTagsCopy(this::copy, Resources.IRON, DOOR);
		forgeTagsCopy(this::copy, Resources.IRON, TRAPDOOR);
		forgeTagsCopy(this::copy, Resources.OBSIDIAN, BLOCK);
		
		forgeTags(this::getBuilder, Resources.COAL, GEM, Items.COAL);
		
		// Add to the existing vanilla tags
		copy(BlockTags.FENCES, ItemTags.FENCES);
		copy(BlockTags.DOORS, ItemTags.DOORS);
		copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
	}
}
