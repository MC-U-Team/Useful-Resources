package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
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
				copy(type.getBlockTag(resource), type.getTag(resource));
				copy(type.getBlockUnifyTag(), type.getUnifyTag());
			});
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getItems().forEach((type, item) -> {
				final Tag<Item> tag = type.getTag(resource);
				getBuilder(tag).add(item);
				getBuilder(type.getUnifyTag()).add(tag);
			});
		});
	}
}
