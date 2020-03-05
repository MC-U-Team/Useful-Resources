package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().forEach((type, block) -> {
				if (type.hasTag()) {
					copy(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
				}
				if (type.hasUnifyTag()) {
					copy(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
				}
			});
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getItems().forEach((type, item) -> {
				if (type.hasTag()) {
					final Tag<Item> tag = type.getTag(resource);
					getBuilder(tag).add(item);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).add(tag);
					}
				}
			});
		});
	}
}
