package info.u_team.useful_resources.data.util;

import java.util.Map;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.util.ResourceLocation;

public class ModelGenerationUtil {
	
	public static ResourceLocation getBaseModel(IResourceType<?> type, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		final Map<String, Object> extraProperties = dataGeneratorConfigurator.getExtraProperties();
		final String name;
		if (extraProperties.containsKey(type.getName() + "ModelOverride")) {
			name = ObjectUtil.getString(extraProperties.get(type.getName() + "ModelOverride"));
		} else {
			name = type.getName();
		}
		return new ResourceLocation(UsefulResourcesMod.MODID, "base/item/special/" + name);
	}
	
}
