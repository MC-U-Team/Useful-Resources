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
	
	public static <T extends IForgeRegistryEntry<T>> void forgeTags(Function<INamedTag<T>, BetterBuilder<T>> builderFunction, IResource resource, IResourceType<T> type, T entry) {
		if (type.hasTag()) {
			final INamedTag<T> tag = type.getTag(resource);
			builderFunction.apply(tag).add(entry);
			if (type.hasUnifyTag()) {
				builderFunction.apply(type.getUnifyTag()).add(tag);
			}
		}
	}
	
	public static void forgeTagsCopy(BiConsumer<INamedTag<Block>, INamedTag<Item>> copyConsumer, IResource resource, IResourceType<Block> type) {
		if (type.hasTag()) {
			copyConsumer.accept(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
		}
		if (type.hasUnifyTag()) {
			copyConsumer.accept(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
		}
	}
	
	@SafeVarargs
	public static <T extends IForgeRegistryEntry<T>> void moreCommonTags(Function<INamedTag<T>, BetterBuilder<T>> builderFunction, Map<? extends IResourceType<T>, RegistryEntry<T>> entries, Function<ResourceLocation, INamedTag<T>> tagFunction, IResource resource, ResourceLocation baseTag, IResourceType<T>... types) {
		final Map<IResourceType<T>, Boolean> hasType = Stream.of(types).collect(MoreCollectors.toLinkedMap(Function.identity(), type -> entries.containsKey(type)));
		if (hasType.containsValue(true)) {
			final ResourceLocation specialTagName = new ResourceLocation(baseTag.getNamespace(), baseTag.getPath() + "/" + resource.getName());
			final INamedTag<T> tag = tagFunction.apply(specialTagName);
			final BetterBuilder<T> builder = builderFunction.apply(tag);
			hasType.entrySet().stream().filter(entry -> entry.getValue().equals(true)).map(Entry::getKey).forEach(type -> {
				builder.add(type.getTag(resource));
			});
			builderFunction.apply(tagFunction.apply(baseTag)).add(tag);
		}
	}
	
	@SafeVarargs
	public static void moreCommonTagsCopy(BiConsumer<INamedTag<Block>, INamedTag<Item>> copyConsumer, IResource resource, ResourceLocation baseTag, IResourceType<Block>... types) {
		final Map<IResourceType<Block>, Boolean> hasType = Stream.of(types).collect(MoreCollectors.toLinkedMap(Function.identity(), type -> resource.getBlocks().containsKey(type)));
		if (hasType.containsValue(true)) {
			final ResourceLocation specialTagName = new ResourceLocation(baseTag.getNamespace(), baseTag.getPath() + "/" + resource.getName());
			copyConsumer.accept(TagUtil.createBlockTag(specialTagName), TagUtil.createItemTag(specialTagName));
			copyConsumer.accept(TagUtil.createBlockTag(baseTag), TagUtil.createItemTag(baseTag));
		}
	}
	
	public static <T extends IForgeRegistryEntry<T>> void conditionTags(Function<INamedTag<T>, BetterBuilder<T>> builderFunction, Map<? extends IResourceType<T>, RegistryEntry<T>> entries, IResourceType<T> type, INamedTag<T> tag) {
		if (entries.containsKey(type)) {
			builderFunction.apply(tag).add(entries.get(type).get());
		}
	}
	
	@SafeVarargs
	public static <T extends IForgeRegistryEntry<T>> void moreCommonUnifyTags(Function<INamedTag<T>, BetterBuilder<T>> builderFunction, Function<ResourceLocation, INamedTag<T>> tagFunction, ResourceLocation baseTag, IResourceType<T>... types) {
		final BetterBuilder<T> builder = builderFunction.apply(tagFunction.apply(baseTag));
		for (final IResourceType<T> type : types) {
			if (type.hasUnifyTag()) {
				builder.add(type.getUnifyTag());
			}
		}
	}
}
