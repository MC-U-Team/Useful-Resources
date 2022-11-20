package info.u_team.useful_resources.resource.common;

import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.resource.ResourceTypeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonResourceTypes {
	
	// Blocks
	public static final ResourceTypeKey<Block> ORE = ResourceTypeBuilder.block("ore").tagName("stone_ores").build();
	public static final ResourceTypeKey<Block> DEEPSLATE_ORE = ResourceTypeBuilder.block("deepslate_ore").defaultRegistryName(name -> "deepslate_" + name + "_ore").build();
	public static final ResourceTypeKey<Block> NETHERRACK_ORE = ResourceTypeBuilder.block("netherrack_ore").defaultRegistryName(name -> "netherrack_" + name + "_ore").build();
	public static final ResourceTypeKey<Block> ENDSTONE_ORE = ResourceTypeBuilder.block("endstone_ore").defaultRegistryName(name -> "endstone_" + name + "_ore").build();
	
	public static final ResourceTypeKey<Item> RAW = ResourceTypeBuilder.item("raw").tagName("raw_materials").defaultRegistryName(name -> "raw_" + name).build();
	
	private CommonResourceTypes() {
	}
	
}
