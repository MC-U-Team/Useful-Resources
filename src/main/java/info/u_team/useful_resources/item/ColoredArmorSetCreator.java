package info.u_team.useful_resources.item;

import info.u_team.u_team_core.item.armor.*;
import info.u_team.u_team_core.util.ItemProperties;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.Item.Properties;

public class ColoredArmorSetCreator {
	
	public static ArmorSet create(String name, Properties properties, IArmorMaterial material) {
		return create(name, null, properties, material);
	}
	
	public static ArmorSet create(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
		return new ArmorSet(new ColoredModelHelmetItem(name, group, new ItemProperties(properties), material), new ColoredModelChestplateItem(name, group, new ItemProperties(properties), material), new ColoredModelLeggingsItem(name, group, new ItemProperties(properties), material), new ColoredModelBootsItem(name, group, new ItemProperties(properties), material));
	}
	
	private static class ColoredModelHelmetItem extends UHelmetItem implements IColoredArmor {
		
		public ColoredModelHelmetItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
			return getArmorTextureString(slot, type);
		}
	}
	
	private static class ColoredModelChestplateItem extends UChestplateItem implements IColoredArmor {
		
		public ColoredModelChestplateItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
			return getArmorTextureString(slot, type);
		}
	}
	
	private static class ColoredModelLeggingsItem extends ULeggingsItem implements IColoredArmor {
		
		public ColoredModelLeggingsItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
			return getArmorTextureString(slot, type);
		}
	}
	
	private static class ColoredModelBootsItem extends UBootsItem implements IColoredArmor {
		
		public ColoredModelBootsItem(String name, ItemGroup group, Properties properties, IArmorMaterial material) {
			super(name, group, properties, material);
		}
		
		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
			return getArmorTextureString(slot, type);
		}
	}
	
	private static String getArmorTextureString(EquipmentSlotType slot, String type) {
		return String.format(UsefulResourcesMod.MODID + ":textures/models/armor/armor_layer_%d%s.png", (slot == EquipmentSlotType.LEGS ? 2 : 1), type == null ? "" : String.format("_%s", type));
	}
}
