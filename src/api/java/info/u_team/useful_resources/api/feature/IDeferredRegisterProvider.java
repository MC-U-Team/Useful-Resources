package info.u_team.useful_resources.api.feature;

import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.DeferredRegister;

public interface IDeferredRegisterProvider {
	
	BlockDeferredRegister getBlockRegister();
	
	DeferredRegister<Fluid> getFluidRegister();
	
	DeferredRegister<Item> getItemRegister();
	
}
