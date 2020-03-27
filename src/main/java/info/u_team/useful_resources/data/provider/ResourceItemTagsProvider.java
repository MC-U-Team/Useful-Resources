package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.item.*;
import net.minecraft.tags.Tag;
import net.minecraft.tags.Tag.Builder;
import net.minecraftforge.common.Tags;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				if (type.hasTag()) {
					copy(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
				}
				if (type.hasUnifyTag()) {
					copy(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
				}
			});
			final boolean hasOre = resource.getBlocks().containsKey(BlockResourceType.ORE);
			final boolean hasNetherOre = resource.getBlocks().containsKey(BlockResourceType.NETHER_ORE);
			if (hasOre || hasNetherOre) {
				final Tag<Item> tag = TagUtil.createItemTag("forge", "ores/" + resource.getName());
				final Builder<Item> builder = getBuilder(tag);
				if (hasOre) {
					builder.add(TagUtil.fromBlockTag(BlockResourceType.ORE.getTag(resource)));
				}
				if (hasNetherOre) {
					builder.add(TagUtil.fromBlockTag(BlockResourceType.NETHER_ORE.getTag(resource)));
				}
				getBuilder(Tags.Items.ORES).add(tag);
			}
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryItems((type, item) -> {
				if (type.hasTag()) {
					final Tag<Item> tag = type.getTag(resource);
					getBuilder(tag).add(item);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
		});
		
		getBuilder(TagUtil.createItemTag("forge", "tools")).add(ItemResourceType.AXE.getUnifyTag(), ItemResourceType.HOE.getUnifyTag(), ItemResourceType.PICKAXE.getUnifyTag(), ItemResourceType.SHOVEL.getUnifyTag(), ItemResourceType.SWORD.getUnifyTag());
		getBuilder(TagUtil.createItemTag("forge", "armors")).add(ItemResourceType.HELMET.getUnifyTag(), ItemResourceType.CHESTPLATE.getUnifyTag(), ItemResourceType.LEGGINGS.getUnifyTag(), ItemResourceType.BOOTS.getUnifyTag());
		
		// Special tags
		
		// Add nether quartz ore to the nether ores tag
		final Tag<Item> netherQuartzOreTag = TagUtil.fromBlockTag(BlockResourceType.NETHER_ORE.getTag(Resources.QUARTZ));
		getBuilder(netherQuartzOreTag).add(Items.NETHER_QUARTZ_ORE);
		getBuilder(TagUtil.fromBlockTag(BlockResourceType.NETHER_ORE.getUnifyTag())).add(netherQuartzOreTag);
		
		// Add coal to the coal gem tag
		final Tag<Item> coalGemTag = ItemResourceType.GEM.getTag(Resources.COAL);
		getBuilder(coalGemTag).add(Items.COAL);
		getBuilder(ItemResourceType.GEM.getUnifyTag()).add(coalGemTag);
	}
}
