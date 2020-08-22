package info.u_team.useful_resources.data.provider;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.resources.Resources;
import info.u_team.useful_resources.util.MoreCollectors;
import net.minecraft.data.TagsProvider.Builder;
import net.minecraft.item.*;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;

public class ResourceItemTagsProvider extends CommonItemTagsProvider {
	
	public ResourceItemTagsProvider(GenerationData data, ResourceBlockTagsProvider blockProvider) {
		super(data, blockProvider);
	}
	
	@SuppressWarnings("unchecked") // TODO not nice :/
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
			
			// Add stone and nether ores to the ore tags
			addMoreCommonTag(resource, new ResourceLocation("forge", "ores"));
			
			// Add crushed stone and crushed nether ores to the crushed ore tags
			addMoreCommonTag(resource, new ResourceLocation("forge", "crushed_ores"), ItemResourceType.CRUSHED_ORE, ItemResourceType.CRUSHED_NETHER_ORE);
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.iterateRegistryItems((type, item) -> {
				if (type.hasTag()) {
					final INamedTag<Item> tag = type.getTag(resource);
					getBuilder(tag).add(item);
					if (type.hasUnifyTag()) {
						getBuilder(type.getUnifyTag()).addTag(tag);
					}
				}
			});
		});
		
		getBuilder(TagUtil.createItemTag("forge", "tools")).addTags(ItemResourceType.AXE.getUnifyTag(), ItemResourceType.HOE.getUnifyTag(), ItemResourceType.PICKAXE.getUnifyTag(), ItemResourceType.SHOVEL.getUnifyTag(), ItemResourceType.SWORD.getUnifyTag());
		getBuilder(TagUtil.createItemTag("forge", "armors")).addTags(ItemResourceType.HELMET.getUnifyTag(), ItemResourceType.CHESTPLATE.getUnifyTag(), ItemResourceType.LEGGINGS.getUnifyTag(), ItemResourceType.BOOTS.getUnifyTag());
		
		// Special tags
		
		// Add vanilla ores to the right tags
		copyBlockTag(BlockResourceType.ORE, Resources.IRON);
		copyBlockTag(BlockResourceType.ORE, Resources.GOLD);
		copyBlockTag(BlockResourceType.ORE, Resources.DIAMOND);
		copyBlockTag(BlockResourceType.ORE, Resources.EMERALD);
		copyBlockTag(BlockResourceType.ORE, Resources.LAPIS);
		copyBlockTag(BlockResourceType.NETHER_ORE, Resources.QUARTZ);
		copyBlockTag(BlockResourceType.ORE, Resources.COAL);
		copyBlockTag(BlockResourceType.ORE, Resources.REDSTONE);
		
		// Add coal to the coal gem tag
		addItemTag(ItemResourceType.GEM, Resources.COAL, Items.COAL);
	}
	
	private void addMoreCommonTag(IResource resource, ResourceLocation baseTag) {
		final String specialTagName = baseTag.getPath() + "/" + resource.getName();
		copy(TagUtil.createBlockTag(baseTag.getNamespace(), specialTagName), TagUtil.createItemTag(baseTag.getNamespace(), specialTagName));
		copy(TagUtil.createBlockTag(baseTag), TagUtil.createItemTag(baseTag));
	}
	
	private void addMoreCommonTag(IResource resource, ResourceLocation baseTag, ItemResourceType... types) {
		final Map<ItemResourceType, Boolean> hasType = Stream.of(types).collect(MoreCollectors.toLinkedMap(Function.identity(), type -> resource.getItems().containsKey(type)));
		if (hasType.containsValue(true)) {
			final INamedTag<Item> tag = TagUtil.createItemTag(baseTag.getNamespace(), baseTag.getPath() + "/" + resource.getName());
			final Builder<Item> builder = getBuilder(tag);
			hasType.entrySet().stream().filter(entry -> entry.getValue().equals(true)).map(Entry::getKey).forEach(type -> {
				builder.addTag(type.getTag(resource));
			});
			getBuilder(TagUtil.createItemTag(baseTag)).addTag(tag);
		}
	}
	
	private void addItemTag(ItemResourceType type, IResource resource, Item item) {
		final INamedTag<Item> tag = type.getTag(resource);
		getBuilder(tag).add(item);
		getBuilder(type.getUnifyTag()).addTag(tag);
	}
	
	private void copyBlockTag(BlockResourceType type, IResource resource) {
		copy(type.getTag(resource), TagUtil.fromBlockTag(type.getTag(resource)));
		copy(type.getUnifyTag(), TagUtil.fromBlockTag(type.getUnifyTag()));
	}
}
