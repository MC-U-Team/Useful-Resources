package info.u_team.useful_resources.init;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.block.OreBlock;
import net.minecraft.client.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = UsefulResourcesMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class UsefulResourcesRenderTypes {
	
	@SubscribeEvent
	public static void register(FMLClientSetupEvent event) {
		// Cutout
		final RenderType cutout = RenderType.getCutout();
		
		ResourceRegistry.getResources().stream() //
				.flatMap(resource -> resource.getBlocks().values().stream()) //
				.filter(block -> block instanceof OreBlock) //
				.map(block -> (OreBlock) block) //
				.forEach(block -> RenderTypeLookup.setRenderLayer(block, cutout));
	}
	
}
