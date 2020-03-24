package info.u_team.useful_resources.api.resource;

import java.util.Map;

import info.u_team.useful_resources.api.feature.IResourceFeatureBuilder;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public interface IResource {
	
	String getName();
	
	int getColor();
	
	ItemResourceType getRepairType();
	
	Map<BlockResourceType, Block> getBlocks();
	
	Map<FluidResourceType, Fluid> getFluids();
	
	Map<ItemResourceType, Item> getItems();
	
	IDataGeneratorConfigurator getDataGeneratorConfigurator();
	
	void clearDataGeneratorConfig();
	
	void addFeature(IResourceFeatureBuilder builder);
	
}
