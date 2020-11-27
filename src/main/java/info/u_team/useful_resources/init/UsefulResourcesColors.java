package info.u_team.useful_resources.init;

import com.google.common.collect.Streams;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulResourcesColors {
	
	private static void colorItem(ColorHandlerEvent.Item event) {
		ResourceRegistry.forEach(resource -> {
			event.getBlockColors().register((state, world, pos, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return -1;
				}
			}, resource.getRegistryBlocks().stream().map(RegistryEntry::get).toArray(Block[]::new));
		});
		
		ResourceRegistry.forEach(resource -> {
			event.getItemColors().register((stack, tintIndex) -> {
				if (tintIndex == 1) {
					return resource.getColor();
				} else {
					return -1;
				}
			}, Streams.concat(resource.getRegistryBlocks().stream().map(RegistryEntry::get).map(Block::asItem), resource.getRegistryItems().stream().map(RegistryEntry::get)).toArray(Item[]::new));
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesColors::colorItem);
	}
	
}
