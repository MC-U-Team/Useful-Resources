package info.u_team.useful_resources.resource;

import static info.u_team.useful_resources.api.resource.CommonResourceBuilder.*;
import static info.u_team.useful_resources.api.type.ItemResourceType.*;
import static info.u_team.useful_resources.util.MaterialUtil.createArmor;
import static info.u_team.useful_resources.util.MaterialUtil.createTools;

import info.u_team.useful_resources.api.resource.BasicResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.ItemResourceType;
import net.minecraft.item.Rarity;

public class ObsidianResource extends BasicResource<ObsidianResource> {
	
	public ObsidianResource(String name, int color) {
		super(name, color, ItemResourceType.DUST, Rarity.COMMON, ResourceType.BLOCK);
		
		addFeature(createBars(Rarity.COMMON, 3, 40, 1200));
		addFeature(createChain(Rarity.COMMON, 3, 40, 1200));
		addFeature(createFence(Rarity.COMMON, 3, 40, 1200));
		addFeature(createDoor(Rarity.COMMON, 3, 40, 1200));
		addFeature(createTrapDoor(Rarity.COMMON, 3, 40, 1200));
		
		addFeature(createMolten(0xFF000000 + color));
		
		addFeature(createBasicItem(PIECE, Rarity.COMMON));
		
		addFeature(createBasicItem(DUST, Rarity.COMMON));
		addFeature(createBasicItem(PLATE, Rarity.COMMON));
		addFeature(createBasicItem(DENSE_PLATE, Rarity.COMMON));
		addFeature(createBasicItem(GEAR, Rarity.COMMON));
		addFeature(createBasicItem(ROD, Rarity.COMMON));
		
		setArmor(createArmor(35, new int[] { 3, 6, 8, 3 }, 25));
		setHorseArmor(15);
		setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 4, 2000, 10, 4, 25));
	}
}
