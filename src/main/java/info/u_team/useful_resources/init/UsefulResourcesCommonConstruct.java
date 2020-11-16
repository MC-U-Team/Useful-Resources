package info.u_team.useful_resources.init;

import info.u_team.u_team_core.api.construct.*;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.config.CommonConfig;
import info.u_team.useful_resources.resources.Resources;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

@Construct(modid = UsefulResourcesMod.MODID)
public class UsefulResourcesCommonConstruct implements IModConstruct {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG);
		
		Resources.register();
		
		BusRegister.registerMod(UsefulResourcesParticleTypes::register);
		BusRegister.registerMod(UsefulResourcesResources::register);
	}
	
}
