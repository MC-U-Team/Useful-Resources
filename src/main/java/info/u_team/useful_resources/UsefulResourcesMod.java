package info.u_team.useful_resources;

import info.u_team.u_team_core.api.IModProxy;
import info.u_team.useful_resources.proxy.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(UsefulResourcesMod.MODID)
public class UsefulResourcesMod {
	
	public static final String MODID = "usefulores";
	
	public static final IModProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
	
	public UsefulResourcesMod() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		PROXY.construct();
	}
	
	@SubscribeEvent
	public void setup(FMLCommonSetupEvent event) {
		PROXY.setup();
	}
	
	@SubscribeEvent
	public void ready(FMLLoadCompleteEvent event) {
		PROXY.complete();
	}
	
}
