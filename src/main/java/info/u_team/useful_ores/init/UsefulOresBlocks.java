package info.u_team.useful_ores.init;

import java.util.List;

import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import info.u_team.useful_ores.UsefulOresMod;
import info.u_team.useful_ores.resource.BlockResourceSet;
import info.u_team.useful_ores.type.Resources;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = UsefulOresMod.MODID, bus = Bus.MOD)
public class UsefulOresBlocks {
	
	public static final BlockResourceSet COPPER = new BlockResourceSet(Resources.COPPER);
	
	@SubscribeEvent
	public static void register(Register<Block> event) {
		entries = BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(UsefulOresMod.MODID, Block.class);
		entries.forEach(event.getRegistry()::register);
	}
	
	@SubscribeEvent
	public static void registerBlockItem(Register<Item> event) {
		BaseRegistryUtil.getBlockItems(entries).forEach(event.getRegistry()::register);
		entries = null; // Dereference list as it is no longer needed
	}
	
	// Just a cache for the block item registry for performance
	private static List<Block> entries;
	
}
