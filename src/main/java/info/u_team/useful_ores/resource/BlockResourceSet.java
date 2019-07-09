package info.u_team.useful_ores.resource;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import info.u_team.useful_ores.api.*;
import info.u_team.useful_ores.block.ResourceBlock;
import net.minecraft.block.*;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;

public class BlockResourceSet implements IUArrayRegistryType<Block> {
	
	private final Block[] resources;
	
	public BlockResourceSet(IResource resource) {
		final IResourceConfig config = resource.getConfig();
		resources = new Block[] { new ResourceBlock("ore", resource, Properties.create(Material.ROCK), config.getOreHardness(), config.getOreResistance()), new ResourceBlock("nether_ore", resource, Properties.create(Material.ROCK), config.getNetherOreHardness(), config.getNetherOreResistance()), new ResourceBlock("block", resource, Properties.create(Material.IRON).sound(SoundType.METAL), config.getBlockHardness(), config.getBlockResistance()) };
	}
	
	@Override
	public Block[] getArray() {
		return resources;
	}
	
}
