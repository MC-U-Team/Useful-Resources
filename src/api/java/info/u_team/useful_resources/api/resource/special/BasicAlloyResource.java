package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.BLOCK;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import java.util.*;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.stack.IItemCountProvider;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class BasicAlloyResource extends BasicResource<BasicAlloyResource> {
	
	private final List<IItemCountProvider> ingredients;
	private final int outputSize;
	
	public BasicAlloyResource(String name, int color, Rarity rarity, int harvestLevel, float baseHardness, int outputSize) {
		super(name, color, ItemResourceType.INGOT, rarity, ResourceType.INGOT);
		ingredients = new ArrayList<>();
		this.outputSize = outputSize;
		
		addFeature(createBasicBlock(BLOCK, rarity, harvestLevel, baseHardness * 2, baseHardness * 4));
		
		addFeature(createMoltenFluid(0xFF000000 + color));
		
		addFeature(createBasicItem(INGOT, rarity));
		addFeature(createBasicItem(NUGGET, rarity));
		addFeature(createBasicItem(DUST, rarity));
		addFeature(createBasicItem(PLATE, rarity));
		addFeature(createBasicItem(DENSE_PLATE, rarity));
		addFeature(createBasicItem(GEAR, rarity));
		addFeature(createBasicItem(ROD, rarity));
	}
	
	public BasicAlloyResource addIngredient(IItemCountProvider provider) {
		ingredients.add(provider);
		return this;
	}
	
	public List<IItemCountProvider> getIngredients() {
		return Collections.unmodifiableList(ingredients);
	}
	
	public int getOutputSize() {
		return outputSize;
	}
}
