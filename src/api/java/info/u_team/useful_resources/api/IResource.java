package info.u_team.useful_resources.api;

import java.util.Map;

import info.u_team.useful_resources.api.feature.IResourceFeatureBuilder;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IResource {
	
	String getName();
	
	int getColor();
	
	Map<BlockResourceType, Block> getBlocks();
	
	Map<ItemResourceType, Item> getItems();
	
	void addFeature(IResourceFeatureBuilder builder);
	
}
