package info.u_team.useful_resources.resource;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.block.ResourceBlock;
import net.minecraft.block.*;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;

public class BlockResourceSet implements IResourceBlocks {
	
	private final Block ore;
	private final Block netherOre;
	private final Block block;
	
	public BlockResourceSet(IResource resource) {
		final IResourceConfig config = resource.getConfig();
		ore = new ResourceBlock("ore", resource, Properties.create(Material.ROCK), config.getOreHardness(), config.getOreResistance());
		netherOre = new ResourceBlock("nether_ore", resource, Properties.create(Material.ROCK), config.getNetherOreHardness(), config.getNetherOreResistance());
		block = new ResourceBlock("block", resource, Properties.create(Material.IRON).sound(SoundType.METAL), config.getBlockHardness(), config.getBlockResistance());
	}
	
	@Override
	public Block getOre() {
		return ore;
	}
	
	@Override
	public Block getNetherOre() {
		return netherOre;
	}
	
	@Override
	public Block getBlock() {
		return block;
	}
}
