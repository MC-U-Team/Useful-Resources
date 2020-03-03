package info.u_team.useful_resources.init;

import com.google.common.collect.Streams;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.ResourceRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UsefulResourcesColors {
	
	@SubscribeEvent
	public static void register(ColorHandlerEvent.Item event) {
		ResourceRegistry.getResources().forEach(resource -> {
			event.getBlockColors().register((state, lightReader, pos, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return 0xFFFFFF;
				}
			}, resource.getBlocks().values().stream().toArray(Block[]::new));
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			event.getItemColors().register((stack, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return 0xFFFFFF;
				}
			}, Streams.concat(resource.getBlocks().values().stream().map(Block::asItem), resource.getItems().values().stream()).toArray(Item[]::new));
		});
	}
	
}
