package info.u_team.useful_resources.init;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.block.IBlockRenderType;
import info.u_team.useful_resources.api.block.IBlockRenderType.BlockRenderType;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UsefulResourcesRenderTypes {
	
	private static void setup(FMLClientSetupEvent event) {
		ResourceRegistry.getResources().stream() //
				.flatMap(resource -> resource.getBlocks().values().stream().map(RegistryEntry::get)) //
				.filter(block -> block instanceof IBlockRenderType) //
				.forEach(block -> {
					BlockRenderType type = ((IBlockRenderType) block).getType();
					if (type != BlockRenderType.SOLID) {
						RenderTypeLookup.setRenderLayer(block, type.getType().get().get());
					}
				});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(UsefulResourcesRenderTypes::setup);
	}
}
