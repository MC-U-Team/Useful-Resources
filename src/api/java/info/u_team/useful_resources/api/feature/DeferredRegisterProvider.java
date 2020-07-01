package info.u_team.useful_resources.api.feature;

import info.u_team.u_team_core.util.registry.*;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

public class DeferredRegisterProvider implements IDeferredRegisterProvider {
	
	private final BlockDeferredRegister blocks;
	private final CommonDeferredRegister<Fluid> fluids;
	private final CommonDeferredRegister<Item> items;
	
	public DeferredRegisterProvider() {
		blocks = BlockDeferredRegister.create(UsefulResourcesMod.MODID);
		fluids = CommonDeferredRegister.create(ForgeRegistries.FLUIDS, UsefulResourcesMod.MODID);
		items = CommonDeferredRegister.create(ForgeRegistries.ITEMS, UsefulResourcesMod.MODID);
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
