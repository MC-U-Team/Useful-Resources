package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockTagsProvider;
import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.IResourceBlocks;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.Tag;

public class ResourceBlockTagsProvider extends CommonBlockTagsProvider {
	
	public ResourceBlockTagsProvider(DataGenerator generator) {
		super("Resources-Block-Tags", generator);
	}
	
	@Override
	protected void registerTags() {
		registerBlocks();
	}
	
	private void registerBlocks() {
		final Tag<Block> oresTag = TagUtil.createBlockTag("forge", "ores");
		final Tag<Block> netherOresTag = TagUtil.createBlockTag("forge", "nether_ores");
		final Tag<Block> blocksTag = TagUtil.createBlockTag("forge", "storage_blocks");
		
		Resources.VALUES.forEach(resource -> {
			final Tag<Block> oreTag = TagUtil.createBlockTag("forge", "ores/" + resource.getName());
			final Tag<Block> netherOreTag = TagUtil.createBlockTag("forge", "nether_ores/" + resource.getName());
			final Tag<Block> blockTag = TagUtil.createBlockTag("forge", "storage_blocks/" + resource.getName());
			
			final IResourceBlocks blocks = resource.getBlocks();
			
			getBuilder(oreTag).add(blocks.getOre());
			getBuilder(netherOreTag).add(blocks.getNetherOre());
			getBuilder(blockTag).add(blocks.getBlock());
			
			getBuilder(oresTag).add(oreTag);
			getBuilder(netherOresTag).add(netherOreTag);
			getBuilder(blocksTag).add(blockTag);
		});
	}
}
