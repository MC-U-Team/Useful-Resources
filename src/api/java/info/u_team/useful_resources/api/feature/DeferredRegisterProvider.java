package info.u_team.useful_resources.api.feature;

import info.u_team.u_team_core.util.registry.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public class DeferredRegisterProvider implements IDeferredRegisterProvider {
	
	private final BlockDeferredRegister blocks;
	private final CommonDeferredRegister<Fluid> fluids;
	private final CommonDeferredRegister<Item> items;
	
	public DeferredRegisterProvider(BlockDeferredRegister blocks, CommonDeferredRegister<Fluid> fluids, CommonDeferredRegister<Item> items) {
		this.blocks = blocks;
		this.fluids = fluids;
		this.items = items;
	}
	
	@Override
	public BlockDeferredRegister getBlockRegister() {
		return blocks;
	}
	
	@Override
	public CommonDeferredRegister<Fluid> getFluidRegister() {
		return fluids;
	}
	
	@Override
	public CommonDeferredRegister<Item> getItemRegister() {
		return items;
	}
	
}
