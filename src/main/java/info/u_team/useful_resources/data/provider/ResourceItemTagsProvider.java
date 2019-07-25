package info.u_team.useful_resources.data.provider;

import info.u_team.useful_resources.api.IResourceItems;
import info.u_team.useful_resources.type.Resources;
import info.u_team.useful_resources.util.TagUtil;
import net.minecraft.block.Block;
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
		final Tag<Item> oresTag = TagUtil.createItemTag("forge", "ores");
		final Tag<Item> netherOresTag = TagUtil.createItemTag("forge", "nether_ores");
		final Tag<Item> blocksTag = TagUtil.createItemTag("forge", "storage_blocks");
		
		Resources.VALUES.forEach(resource -> {
			
			final Tag<Block> oreBlockTag = TagUtil.createBlockTag("forge", "ores/" + resource.getName());
			final Tag<Block> netherOreBlockTag = TagUtil.createBlockTag("forge", "nether_ores/" + resource.getName());
			final Tag<Block> blockBlockTag = TagUtil.createBlockTag("forge", "storage_blocks/" + resource.getName());
			
			final Tag<Item> oreItemTag = TagUtil.fromBlockTag(oreBlockTag);
			final Tag<Item> netherOreItemTag = TagUtil.fromBlockTag(netherOreBlockTag);
			final Tag<Item> blockItemTag = TagUtil.fromBlockTag(blockBlockTag);
			
			copy(oreBlockTag, oreItemTag);
			copy(netherOreBlockTag, netherOreItemTag);
			copy(blockBlockTag, blockItemTag);
			
			getBuilder(oresTag).add(oreItemTag);
			getBuilder(netherOresTag).add(netherOreItemTag);
			getBuilder(blocksTag).add(blockItemTag);
		});
	}
	
	private void registerItems() {
		final Tag<Item> ingotsTag = TagUtil.createItemTag("forge", "ingots");
		final Tag<Item> nuggetsTag = TagUtil.createItemTag("forge", "nuggets");
		final Tag<Item> dustsTag = TagUtil.createItemTag("forge", "dusts");
		final Tag<Item> platesTag = TagUtil.createItemTag("forge", "plates");
		final Tag<Item> densePlatesTag = TagUtil.createItemTag("forge", "dense_plates");
		final Tag<Item> gearsTag = TagUtil.createItemTag("forge", "gears");
		final Tag<Item> rodsTag = TagUtil.createItemTag("forge", "rods");
		
		Resources.VALUES.forEach(resource -> {
			final Tag<Item> ingotTag = TagUtil.createItemTag("forge", "ingots/" + resource.getName());
			final Tag<Item> nuggetTag = TagUtil.createItemTag("forge", "nuggets/" + resource.getName());
			final Tag<Item> dustTag = TagUtil.createItemTag("forge", "dusts/" + resource.getName());
			final Tag<Item> plateTag = TagUtil.createItemTag("forge", "plates/" + resource.getName());
			final Tag<Item> densePlateTag = TagUtil.createItemTag("forge", "dense_plates/" + resource.getName());
			final Tag<Item> gearTag = TagUtil.createItemTag("forge", "gears/" + resource.getName());
			final Tag<Item> rodTag = TagUtil.createItemTag("forge", "rods/" + resource.getName());
			
			final IResourceItems items = resource.getItems();
			
			getBuilder(ingotTag).add(items.getIngot());
			getBuilder(nuggetTag).add(items.getNugget());
			getBuilder(dustTag).add(items.getDust());
			getBuilder(plateTag).add(items.getPlate());
			getBuilder(densePlateTag).add(items.getDensePlate());
			getBuilder(gearTag).add(items.getGear());
			getBuilder(rodTag).add(items.getRod());
			
			getBuilder(ingotsTag).add(ingotTag);
			getBuilder(nuggetsTag).add(nuggetTag);
			getBuilder(dustsTag).add(dustTag);
			getBuilder(platesTag).add(plateTag);
			getBuilder(densePlatesTag).add(densePlateTag);
			getBuilder(gearsTag).add(gearTag);
			getBuilder(rodsTag).add(rodTag);
		});
	}
}
