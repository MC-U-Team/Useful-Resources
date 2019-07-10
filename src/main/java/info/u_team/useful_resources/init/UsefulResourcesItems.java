package info.u_team.useful_resources.init;

import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.resource.ItemResourceSet;
import info.u_team.useful_resources.type.Resources;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD)
public class UsefulResourcesItems {
	
	public static final ItemResourceSet COPPER = new ItemResourceSet(Resources.COPPER);
	
	@SubscribeEvent
	public static void register(Register<Item> event) {
		BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(UsefulResourcesMod.MODID, Item.class).forEach(event.getRegistry()::register);
	}
	
}
