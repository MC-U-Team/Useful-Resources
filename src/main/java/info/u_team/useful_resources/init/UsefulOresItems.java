package info.u_team.useful_resources.init;

import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_resources.UsefulOresMod;
import info.u_team.useful_resources.resource.ItemResourceSet;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulOresMod.MODID, bus = Bus.MOD)
public class UsefulOresItems {
	
	public static final ItemResourceSet COPPER = new ItemResourceSet(Resources.COPPER);
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(UsefulOresMod.MODID, Item.class).forEach(event.getRegistry()::register);
	}
	
}
