package info.u_team.useful_resources.data.util;

import java.util.function.*;

import info.u_team.u_team_core.data.CommonTagsProvider.BetterBuilder;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class TagGenerationUtil {
	
	public static <T extends IForgeRegistryEntry<T>> void forgeTags(Function<INamedTag<T>, BetterBuilder<T>> function, IResource resource, IResourceType<T> type, T entry) {
		if (type.hasTag()) {
			final INamedTag<T> tag = type.getTag(resource);
			function.apply(tag).add(entry);
			if (type.hasUnifyTag()) {
				function.apply(type.getUnifyTag()).add(tag);
			}
		}
	}
	
	public static void forgeTagsCopy(BiConsumer<INamedTag<Block>, INamedTag<Item>> consumer, IResource resource, IResourceType<Block> type, Block block) {
		if (type.hasTag()) {
			consumer.accept(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
		}
		if (type.hasUnifyTag()) {
			consumer.accept(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
		}
	}
	
}
