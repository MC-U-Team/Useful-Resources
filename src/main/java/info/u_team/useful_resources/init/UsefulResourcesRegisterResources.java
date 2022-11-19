package info.u_team.useful_resources.init;

import org.objectweb.asm.Type;

import info.u_team.u_team_core.util.annotation.AnnotationUtil;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.Resources;
import net.minecraftforge.forgespi.language.ModFileScanData.AnnotationData;

public class UsefulResourcesRegisterResources {
	
	public static void register() {
		for (AnnotationData data : AnnotationUtil.getAnnotations(UsefulResourcesMod.MODID, Type.getType(Resources.class))) {
			try {
				Class.forName(data.memberName());
			} catch (ClassNotFoundException ex) {
				throw new RuntimeException("Failed to register resources with annotation on class " + data.memberName(), ex);
			}
		}
	}
}
