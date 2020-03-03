package info.u_team.useful_resources.item;

import com.google.common.base.MoreObjects;

import net.minecraft.client.Minecraft;
import net.minecraft.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public interface IColoredArmor extends IDyeableArmorItem {
	
	@Override
	default int getColor(ItemStack stack) {
		return MoreObjects.firstNonNull(DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().getItemColors().getColor(stack, 1)), 0);
	}
	
	// Remove methods for nbt
	
	@Override
	default boolean hasColor(ItemStack stack) {
		return false;
	}
	
	@Override
	default void removeColor(ItemStack stack) {
	}
	
	@Override
	default void setColor(ItemStack stack, int color) {
	}
	
}
