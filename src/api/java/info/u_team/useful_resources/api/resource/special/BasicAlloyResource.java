package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.BlockResourceType.BLOCK;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;

import java.util.*;

import com.google.common.base.Supplier;

import info.u_team.useful_resources.api.resource.*;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class BasicAlloyResource extends BasicResource<BasicAlloyResource> {
	
	private Map<IResource, Integer> ingredients;
	
	private Map<Supplier<IResource>, Integer> supplierIngredients;
	
	public BasicAlloyResource(String name, int color, Rarity rarity, int harvestLevel, float baseHardness) {
		super(name, color, ItemResourceType.INGOT, rarity, ResourceType.INGOT);
		ingredients = new HashMap<>();
		supplierIngredients = new HashMap<>();
		
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
	
	public BasicAlloyResource addIngredient(Supplier<IResource> resource, int count) {
		supplierIngredients.put(resource, count);
		return this;
	}
	
	public Map<IResource, Integer> getIngredients() {
		if (ingredients.isEmpty() && !supplierIngredients.isEmpty()) {
			supplierIngredients.forEach((supplier, count) -> ingredients.put(supplier.get(), count));
			supplierIngredients.clear();
		}
		return Collections.unmodifiableMap(ingredients);
	}
}
