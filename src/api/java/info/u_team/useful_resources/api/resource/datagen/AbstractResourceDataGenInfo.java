package info.u_team.useful_resources.api.resource.datagen;

import java.util.Collections;
import java.util.Map;

import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface AbstractResourceDataGenInfo {
	
	static AbstractResourceDataGenInfo empty() {
		return new AbstractResourceDataGenInfo() {
			
			@Override
			public ResourceDataCategory getCategory() {
				return ResourceDataCategory.IRON_LIKE;
			}
			
			@Override
			public Map<ResourceTypeKey<Block>, String> getBlockModelOverride() {
				return Collections.emptyMap();
			}
			
			@Override
			public Map<ResourceTypeKey<Item>, String> getItemModelOverride() {
				return Collections.emptyMap();
			}
		};
	}
	
	// TODO
	// getNormaleResourceType
	// getTinyResourceType
	
	ResourceDataCategory getCategory();
	
	Map<ResourceTypeKey<Block>, String> getBlockModelOverride();
	
	Map<ResourceTypeKey<Item>, String> getItemModelOverride();
}
