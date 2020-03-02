package info.u_team.useful_resources.block;

import info.u_team.u_team_core.block.UBlock;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

public class BasicBlock extends UBlock {
	
	public BasicBlock(String name, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		super(name, UsefulResourcesItemGroups.GROUP, Properties.create(Material.IRON).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(harvestLevel).hardnessAndResistance(hardness, resistance), new Item.Properties().rarity(rarity));
	}
	
}
