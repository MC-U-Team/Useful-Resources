package info.u_team.useful_resources.data.util;

import java.util.function.BiConsumer;
import java.util.function.Function;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import net.minecraft.data.tags.TagsProvider.TagAppender;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagGenerationUtil {
	
	public static <T> void forgeTags(Function<TagKey<T>, TagAppender<T>> function, AbstractResource resource, AbstractResourceType<T> type, T entry) {
		if (type.hasTag()) {
			final TagKey<T> tag = type.getTag(resource);
			function.apply(tag).add(entry);
			if (type.hasUnifyTag()) {
				function.apply(type.getUnifyTag()).addTag(tag);
			}
		}
	}
	
	public static void forgeTagsCopy(BiConsumer<TagKey<Block>, TagKey<Item>> consumer, AbstractResource resource, AbstractResourceType<Block> type) {
		if (type.hasTag()) {
			consumer.accept(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
			if (type.hasUnifyTag()) {
				consumer.accept(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
			}
		}
	}
	
}
