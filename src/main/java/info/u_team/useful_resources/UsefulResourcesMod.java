package info.u_team.useful_resources;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import net.minecraftforge.fml.common.Mod;

@Mod(UsefulResourcesMod.MODID)
public class UsefulResourcesMod {
	
	public static final String MODID = "usefulresources";
	
	public UsefulResourcesMod() {
		JarSignVerifier.checkSigned(MODID);
		
		AnnotationManager.callAnnotations(MODID);
	}
	
}
