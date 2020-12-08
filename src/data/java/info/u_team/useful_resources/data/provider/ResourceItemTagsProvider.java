package info.u_team.useful_resources.data.provider;

import static info.u_team.useful_resources.data.util.TagGenerationUtil.*;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.data.resource.TagGenerationResources;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.item.*;
import net.minecraft.tags.*;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data, CommonBlockTagsProvider blockProvider) {
		super(data, blockProvider);
	}
	
	@Override
	protected void registerTags() {
		TagGenerationResources.forEachBlock((resource, type, block) -> forgeTagsCopy(this::copy, resource, type, block));
		TagGenerationResources.forEachItem((resource, type, item) -> forgeTags(this::getBuilder, resource, type, item));
		
		TagGenerationResources.forEach(resource -> {
			// Add stone, nether and end ores to the ore tags
			moreCommonTagsCopy(this::copy, resource, new ResourceLocation("forge", "ores"), BlockResourceType.ORE, BlockResourceType.NETHER_ORE, BlockResourceType.END_ORE);
			
			// Add crushed stone, nether and end ores to the crushed ore tags
			moreCommonTags(resource.getItems(), this::getBuilder, TagUtil::createItemTag, resource, new ResourceLocation("forge", "crushed_ores"), ItemResourceType.CRUSHED_ORE, ItemResourceType.CRUSHED_NETHER_ORE, ItemResourceType.CRUSHED_END_ORE);
		});
		
		getBuilder(TagUtil.createItemTag("forge", "tools")).add(ItemResourceType.AXE.getUnifyTag(), ItemResourceType.HOE.getUnifyTag(), ItemResourceType.PICKAXE.getUnifyTag(), ItemResourceType.SHOVEL.getUnifyTag(), ItemResourceType.SWORD.getUnifyTag());
		getBuilder(TagUtil.createItemTag("forge", "armors")).add(ItemResourceType.HELMET.getUnifyTag(), ItemResourceType.CHESTPLATE.getUnifyTag(), ItemResourceType.LEGGINGS.getUnifyTag(), ItemResourceType.BOOTS.getUnifyTag());
		
		// Special tags
		
		// Add vanilla blocks to the right tags
		copyBlockTag(BlockResourceType.ORE, Resources.IRON);
		copyBlockTag(BlockResourceType.ORE, Resources.GOLD);
		copyBlockTag(BlockResourceType.NETHER_ORE, Resources.GOLD);
		copyBlockTag(BlockResourceType.ORE, Resources.DIAMOND);
		copyBlockTag(BlockResourceType.ORE, Resources.EMERALD);
		copyBlockTag(BlockResourceType.ORE, Resources.LAPIS);
		copyBlockTag(BlockResourceType.NETHER_ORE, Resources.QUARTZ);
		copyBlockTag(BlockResourceType.ORE, Resources.COAL);
		copyBlockTag(BlockResourceType.ORE, Resources.REDSTONE);
		copyBlockTag(BlockResourceType.BARS, Resources.IRON);
		copyBlockTag(BlockResourceType.CHAIN, Resources.IRON);
		copyBlockTag(BlockResourceType.DOOR, Resources.IRON);
		copyBlockTag(BlockResourceType.TRAPDOOR, Resources.IRON);
		
		// Add coal to the coal gem tag
		addItemTag(ItemResourceType.GEM, Resources.COAL, Items.COAL);
		
		// Add fence and doors to the vanilla tags
		copy(BlockTags.FENCES, ItemTags.FENCES);
		copy(BlockTags.DOORS, ItemTags.DOORS);
		copy(BlockTags.TRAPDOORS, ItemTags.TRAPDOORS);
	}
	
	private void addItemTag(ItemResourceType type, IResource resource, Item item) {
		final INamedTag<Item> tag = type.getTag(resource);
		getBuilder(tag).add(item);
		getBuilder(type.getUnifyTag()).add(tag);
	}
	
	private void copyBlockTag(BlockResourceType type, IResource resource) {
		copy(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
		copy(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
	}
	
}
