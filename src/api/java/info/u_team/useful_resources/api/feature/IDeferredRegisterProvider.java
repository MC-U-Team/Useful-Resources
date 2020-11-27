package info.u_team.useful_resources.api.feature;

import info.u_team.u_team_core.util.registry.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

public interface IDeferredRegisterProvider {
	
	BlockDeferredRegister getBlockRegister();
	
	CommonDeferredRegister<Fluid> getFluidRegister();
	
	CommonDeferredRegister<Item> getItemRegister();
	
	void register(IEventBus bus);
	
}
