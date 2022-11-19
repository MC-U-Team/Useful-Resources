package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.data.util.GenerationResourceRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class UsefulResourcesBlockTagsProvider extends CommonBlockTagsProvider {
	
	public UsefulResourcesBlockTagsProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		GenerationResourceRegistry.forEachBlock((resource, type, block) -> {
			if (type.hasTag()) {
				final TagKey<Block> tag = type.getTag(resource);
				tag(tag).add(block);
				if (type.hasUnifyTag()) {
					tag(type.getUnifyTag()).addTag(tag);
				}
			}
		});
	}
}
