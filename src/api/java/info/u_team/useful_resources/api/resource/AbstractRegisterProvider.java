package info.u_team.useful_resources.api.resource;

import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;

public interface AbstractRegisterProvider {
	
	BlockDeferredRegister getBlockRegister();
	
	CommonDeferredRegister<Fluid> getFluidRegister();
	
	CommonDeferredRegister<Item> getItemRegister();
	
	void register(IEventBus bus);
	
}
