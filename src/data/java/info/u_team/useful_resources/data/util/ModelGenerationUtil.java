package info.u_team.useful_resources.data.util;

import java.util.Map;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ModelGenerationUtil {
	
	public static <T extends IForgeRegistryEntry<T>> ResourceLocation getBaseModel(IResourceType<T> type, T entry, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		final String basePath;
		if (entry instanceof Block) {
			basePath = "block";
		} else if (entry instanceof Item) {
			basePath = "item";
		} else {
			basePath = "general";
		}
		return getBaseModel(type, basePath, dataGeneratorConfigurator);
	}
	
	public static <T extends IForgeRegistryEntry<T>> ResourceLocation getBaseModel(IResourceType<T> type, String basePath, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		final Map<String, Object> extraProperties = dataGeneratorConfigurator.getProperties();
		final String name;
		if (extraProperties.containsKey(type.getName() + "ModelOverride")) {
			name = ObjectUtil.getString(extraProperties.get(type.getName() + "ModelOverride"));
		} else {
			name = type.getName();
		}
		return new ResourceLocation(UsefulResourcesMod.MODID, "base/" + basePath + "/special/" + name);
	}
	
}
