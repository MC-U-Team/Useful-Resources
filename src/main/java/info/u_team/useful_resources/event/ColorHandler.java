package info.u_team.useful_resources.event;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.resources.Resources;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ColorHandler {
	
	// @SubscribeEvent
	// public static void register(ColorHandlerEvent.Item event) {
	// event.getItemColors().register((itemstack, index) -> {
	// return 0xeb4034;
	// }, Resources.COPPER.getItems().get(ItemResourceType.INGOT));
	// }
	
	@SubscribeEvent
	public static void register(ColorHandlerEvent.Block event) {
		event.getBlockColors().register((state, world, pos, index) -> {
			System.out.println(index);
			return 0xeb4034;
		}, Resources.COPPER.getBlocks().get(BlockResourceType.BLOCK), Resources.COPPER.getBlocks().get(BlockResourceType.ORE));
	}
	
}
