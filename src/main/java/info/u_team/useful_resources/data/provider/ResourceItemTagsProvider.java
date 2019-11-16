package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.type.*;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerTags() {
		Resources.getValues().forEach(resource -> {
			final IResourceBlocks blocks = resource.getBlocks();
			ResourceBlockTypes.VALUES.stream().filter(blocks::hasBlock).forEach(type -> {
				copy(blocks.getBlockTag(type), blocks.getTag(type));
				copy(blocks.getUnifyBlockTag(type), blocks.getUnifyTag(type));
			});
		});
		Resources.getValues().forEach(resource -> {
			final IResourceItems items = resource.getItems();
			ResourceItemTypes.VALUES.stream().filter(items::hasItem).forEach(type -> {
				final Tag<Item> tag = items.getTag(type);
				getBuilder(tag).add(items.getItem(type));
				getBuilder(items.getUnifyTag(type)).add(tag);
			});
		});
	}
}
