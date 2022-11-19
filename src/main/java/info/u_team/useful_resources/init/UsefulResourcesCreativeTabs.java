package info.u_team.useful_resources.init;

import info.u_team.u_team_core.creativetab.UCreativeModeTab;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

public class UsefulResourcesCreativeTabs {
	
	public static final CreativeModeTab TAB = new UCreativeModeTab(new ResourceLocation(UsefulResourcesMod.MODID, "tab"), () -> Items.ACACIA_BOAT);
	
}
