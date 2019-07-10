package info.u_team.useful_resources.resource;

import java.util.function.Supplier;

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
		final Supplier<IResourceConfig> config = resource.getConfig();
		ore = new ResourceBlock("ore", resource, Properties.create(Material.ROCK), () -> config.get().getOreHardness().get(), () -> config.get().getOreResistance().get());
		netherOre = new ResourceBlock("nether_ore", resource, Properties.create(Material.ROCK), () -> config.get().getNetherOreHardness().get(), () -> config.get().getNetherOreResistance().get());
		block = new ResourceBlock("block", resource, Properties.create(Material.IRON).sound(SoundType.METAL), () -> config.get().getBlockHardness().get(), () -> config.get().getBlockResistance().get());
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
