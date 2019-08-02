package info.u_team.useful_resources.item;

import info.u_team.u_team_core.api.registry.IURegistryType;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.config.IResourceItemConfig;
import info.u_team.useful_resources.api.resource.type.IResourceItemType;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.util.ResourceLocation;

public class ResourceHorseArmorItem extends HorseArmorItem implements IURegistryType {
	
	protected final String name;
	
	public ResourceHorseArmorItem(IResource resource, IResourceItemType type, IResourceItemConfig config) {
		super(5, new ResourceLocation(UsefulResourcesMod.MODID, "textures/entity/horse/armor/" + resource.getName() + ".png"), new Properties().group(UsefulResourcesItemGroups.GROUP).rarity(config.getRarity()));
		name = resource.getName() + "_" + type.getName();
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
	
}
