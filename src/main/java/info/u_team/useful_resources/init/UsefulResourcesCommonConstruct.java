package info.u_team.useful_resources.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.resource.register.RegisterProvider;

@Construct(modid = UsefulResourcesMod.MODID)
public class UsefulResourcesCommonConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerMod(RegisterProvider.DEFAULT::register);
		
		UsefulResourcesRegisterResources.register();
		// Class.forName();
	}
	
}
