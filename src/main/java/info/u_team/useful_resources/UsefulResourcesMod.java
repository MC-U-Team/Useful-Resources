package info.u_team.useful_resources;

import info.u_team.u_team_core.util.verify.JarSignVerifier;
import info.u_team.useful_resources.config.CommonConfig;
import info.u_team.useful_resources.resources.Resources;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;

@Mod(UsefulResourcesMod.MODID)
public class UsefulResourcesMod {
	
	public static final String MODID = "usefulresources";
	
	public UsefulResourcesMod() {
		JarSignVerifier.checkSigned(MODID);
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG);
		Resources.register();
	}
}
