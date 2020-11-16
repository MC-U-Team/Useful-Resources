package info.u_team.useful_resources.init;

import com.google.common.collect.Streams;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.registry.RegistryEntry;
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
			event.getBlockColors().register((state, world, pos, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return -1;
				}
			}, resource.getRegistryBlocks().stream().map(RegistryEntry::get).toArray(Block[]::new));
		});
		
		ResourceRegistry.getResources().forEach(resource -> {
			event.getItemColors().register((stack, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return -1;
				}
			}, Streams.concat(resource.getRegistryBlocks().stream().map(RegistryEntry::get).map(Block::asItem), resource.getRegistryItems().stream().map(RegistryEntry::get)).toArray(Item[]::new));
		});
	}
	
}
