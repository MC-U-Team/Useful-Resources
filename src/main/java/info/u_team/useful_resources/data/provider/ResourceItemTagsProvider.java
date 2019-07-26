package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonItemTagsProvider;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.type.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(DataGenerator generator) {
		super("Resources-Item-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		registerBlocks();
		registerItems();
	}
	
	private void registerBlocks() {
		Resources.VALUES.forEach(resource -> {
			final IResourceBlocks blocks = resource.getBlocks();
			BlockResourceTypes.VALUES.forEach(type -> {
				copy(blocks.getBlockTag(type), blocks.getTag(type));
				copy(blocks.getUnifyBlockTag(type), blocks.getUnifyTag(type));
			});
		});
	}
	
	private void registerItems() {
		Resources.VALUES.forEach(resource -> {
			final IResourceItems items = resource.getItems();
			ItemResourceTypes.VALUES.forEach(type -> {
				final Tag<Item> tag = items.getTag(type);
				getBuilder(tag).add(items.getItem(type));
				getBuilder(items.getUnifyTag(type)).add(tag);
			});
		});
	}
}
