package info.u_team.useful_resources.data.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.*;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.CommonTagsProvider.BetterBuilder;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.IResourceType;
import info.u_team.useful_resources.util.MoreCollectors;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
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
	
	@SafeVarargs
	public static <T extends IForgeRegistryEntry<T>> void addMoreCommonTag(Map<IResourceType<T>, RegistryEntry<T>> entries, Function<INamedTag<T>, BetterBuilder<T>> builderFunction, Function<ResourceLocation, INamedTag<T>> tagFunction, IResource resource, ResourceLocation baseTag, IResourceType<T>... types) {
		final Map<IResourceType<T>, Boolean> hasType = Stream.of(types).collect(MoreCollectors.toLinkedMap(Function.identity(), type -> entries.containsKey(type)));
		if (hasType.containsValue(true)) {
			final INamedTag<T> tag = tagFunction.apply(new ResourceLocation(baseTag.getNamespace(), baseTag.getPath() + "/" + resource.getName()));
			final BetterBuilder<T> builder = builderFunction.apply(tag);
			hasType.entrySet().stream().filter(entry -> entry.getValue().equals(true)).map(Entry::getKey).forEach(type -> {
				builder.add(type.getTag(resource));
			});
			builderFunction.apply(tagFunction.apply(baseTag)).add(tag);
		}
	}
	
}
