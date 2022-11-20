package info.u_team.useful_resources.init;

import info.u_team.u_team_core.api.construct.Construct;
import info.u_team.u_team_core.api.construct.ModConstruct;
import info.u_team.u_team_core.util.registry.BusRegister;
import info.u_team.useful_resources.UsefulResourcesMod;

@Construct(modid = UsefulResourcesMod.MODID, client = true)
public class UsefulResourcesClientConstruct implements ModConstruct {
	
	@Override
	public void construct() {
		BusRegister.registerForge(UsefulResourcesDebugTags::registerForge);
	}
	
}
