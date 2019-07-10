package info.u_team.useful_resources.resource;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.block.ResourceBlock;
import net.minecraft.block.*;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;

public class BlockResourceSet implements IUArrayRegistryType<Block> {
	
	private final Block[] resources;
	
	private final Block ore, netherOre;
	
	public BlockResourceSet(IResource resource) {
		final IResourceConfig config = resource.getConfig();
		resources = new Block[] { ore = new ResourceBlock("ore", resource, Properties.create(Material.ROCK), config.getOreHardness(), config.getOreResistance()), netherOre = new ResourceBlock("nether_ore", resource, Properties.create(Material.ROCK), config.getNetherOreHardness(), config.getNetherOreResistance()), new ResourceBlock("block", resource, Properties.create(Material.IRON).sound(SoundType.METAL), config.getBlockHardness(), config.getBlockResistance()) };
	}
	
	@Override
	public Block[] getArray() {
		return resources;
	}
	
	public Block getOre() {
		return ore;
	}
	
	public Block getNetherOre() {
		return netherOre;
	}
	
}
