package info.u_team.useful_resources.init;

import java.util.List;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class UsefulResourcesDebugTags {
	
	private static void itemTooltip(ItemTooltipEvent event) {
		final Player player = event.getEntity();
		if (player == null) {
			return;
		}
		if (Screen.hasShiftDown()) {
			final List<Component> tooltips = event.getToolTip();
			
			tooltips.add(Component.empty());
			tooltips.add(Component.literal("Item Tags").withStyle(ChatFormatting.AQUA));
			final ItemStack stack = event.getItemStack();
			stack.getTags().map(TagKey::location).sorted(ResourceLocation::compareNamespaced).forEach(tag -> {
				tooltips.add(Component.literal(" " + tag).withStyle(ChatFormatting.GRAY));
			});
			
			if (stack.getItem() instanceof BlockItem item) {
				tooltips.add(Component.empty());
				tooltips.add(Component.literal("Block Tags").withStyle(ChatFormatting.GREEN));
				final BlockState state = item.getBlock().defaultBlockState();
				state.getTags().map(TagKey::location).sorted(ResourceLocation::compareNamespaced).forEach(tag -> {
					tooltips.add(Component.literal(" " + tag).withStyle(ChatFormatting.GRAY));
				});
			}
		}
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(UsefulResourcesDebugTags::itemTooltip);
	}
	
}
