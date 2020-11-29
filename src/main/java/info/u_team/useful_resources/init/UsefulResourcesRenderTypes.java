package info.u_team.useful_resources.init;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.block.IBlockRenderType;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import net.minecraft.client.renderer.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UsefulResourcesRenderTypes {
	
	private static void setup(FMLClientSetupEvent event) {
		ResourceRegistry.getResources().stream() //
				.flatMap(resource -> resource.getBlocks().values().stream().map(RegistryEntry::get)) //
				.filter(block -> block instanceof IBlockRenderType) //
				.forEach(block -> {
					RenderTypeLookup.setRenderLayer(block, ((IBlockRenderType) block).getType().getType().get().get());
				});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesRenderTypes::setup);
	}
}
