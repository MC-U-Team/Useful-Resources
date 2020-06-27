package info.u_team.useful_resources.item;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.material.IColoredArmor;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;

public class BasicHorseArmorItem extends DyeableHorseArmorItem implements IColoredArmor {
	
	public BasicHorseArmorItem(Rarity rarity, int armorPoints) {
		super(armorPoints, new ResourceLocation(UsefulResourcesMod.MODID, "textures/entity/horse/armor/horse_armor.png"), new Properties().group(UsefulResourcesItemGroups.GROUP));
	}
}
