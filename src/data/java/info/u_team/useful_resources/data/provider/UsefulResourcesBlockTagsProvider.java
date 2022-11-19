package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.api.registry.ExistingRegistryEntry;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import info.u_team.useful_resources.api.resource.ResourceRegistry;
import info.u_team.useful_resources.resources.OreResources;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class UsefulResourcesBlockTagsProvider extends CommonBlockTagsProvider {
	
	public UsefulResourcesBlockTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		ResourceRegistry.getResources() //
				.stream() //
				.flatMap(resource -> resource.getEntries().getBlocks().entrySet().stream()) //
				.filter(entry -> !(entry.getValue() instanceof ExistingRegistryEntry)) //
				.forEach(entry -> {
					final AbstractResourceType<Block> type = entry.getKey().getType();
					final Block block = entry.getValue().get();
					if (type.hasTag()) {
						final TagKey<Block> tag = type.getTag(OreResources.SILVER); // TODO FIX HARD CODE FOR NOW
						tag(tag).add(block);
						if (type.hasUnifyTag()) {
							tag(type.getUnifyTag()).addTag(tag);
						}
					}
				});
		;
	}
	
}
