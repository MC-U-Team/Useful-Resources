package info.u_team.move_to_u_team_core;

import java.util.function.Supplier;

import info.u_team.u_team_core.api.registry.IUBlockRegistryType;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BlockItem;

public class UFluidBlock extends FlowingFluidBlock implements IUBlockRegistryType {
	
	protected final String name;
	
	public UFluidBlock(String name, Properties properties, Supplier<? extends FlowingFluid> supplier) {
		super(supplier, properties);
		this.name = name;
	}
	
	@Override
	public String getEntryName() {
		return name;
	}
	
	@Override
	public BlockItem getBlockItem() {
		return null;
	}
	
}
