package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.item.*;
import net.minecraft.tags.Tag;

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
		
		// Quartz tag
		final Tag<Item> netherQuartzOreTag = TagUtil.fromBlockTag(BlockResourceType.NETHER_ORE.getTag(Resources.QUARTZ));
		getBuilder(netherQuartzOreTag).add(Items.NETHER_QUARTZ_ORE);
		getBuilder(TagUtil.fromBlockTag(BlockResourceType.NETHER_ORE.getUnifyTag())).add(netherQuartzOreTag);
		
		// Coal tag
		final Tag<Item> coalGemTag = ItemResourceType.GEM.getTag(Resources.COAL);
		getBuilder(coalGemTag).add(Items.COAL);
		getBuilder(ItemResourceType.GEM.getUnifyTag()).add(coalGemTag);
	}
}
