package info.u_team.useful_resources.api.feature;

import info.u_team.u_team_core.util.registry.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;

public interface IDeferredRegisterProvider {
	
	BlockDeferredRegister getBlockRegister();
	
	CommonDeferredRegister<Fluid> getFluidRegister();
	
	CommonDeferredRegister<Item> getItemRegister();
	
}
