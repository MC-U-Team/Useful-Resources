package info.u_team.useful_resources.api.material;

import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.util.ItemProperties;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;

public class ColoredArmorSetCreator {
	
	public static ArmorSet create(CommonDeferredRegister<Item> register, String name, Properties properties, IArmorMaterial material) {
		return create(register, name, null, properties, material);
	}
	
	public static ArmorSet create(CommonDeferredRegister<Item> register, String name, ItemGroup group, Properties properties, IArmorMaterial material) {
		final RegistryObject<UHelmetItem> helmet = register.register(name + "_helmet", () -> new ColoredModelHelmetItem(name, group, new ItemProperties(properties), material));
		final RegistryObject<UChestplateItem> chestplate = register.register(name + "_chestplate", () -> new ColoredModelChestplateItem(name, group, new ItemProperties(properties), material));
		final RegistryObject<ULeggingsItem> leggings = register.register(name + "_leggings", () -> new ColoredModelLeggingsItem(name, group, new ItemProperties(properties), material));
		final RegistryObject<UBootsItem> boots = register.register(name + "_boots", () -> new ColoredModelBootsItem(name, group, new ItemProperties(properties), material));
		
		return new ArmorSet(helmet, chestplate, leggings, boots);
	}
	
	private static class ColoredModelHelmetItem extends UHelmetItem implements IColoredArmor {
		
		public ColoredModelHelmetItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
	}
	
	private static class ColoredModelChestplateItem extends UChestplateItem implements IColoredArmor {
		
		public ColoredModelChestplateItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
	}
	
	private static class ColoredModelLeggingsItem extends ULeggingsItem implements IColoredArmor {
		
		public ColoredModelLeggingsItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
	}
	
	private static class ColoredModelBootsItem extends UBootsItem implements IColoredArmor {
		
		public ColoredModelBootsItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
	}
}
