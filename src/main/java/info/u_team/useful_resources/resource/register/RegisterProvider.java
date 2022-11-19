package info.u_team.useful_resources.resource.register;

import info.u_team.u_team_core.util.registry.BlockDeferredRegister;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;

public class RegisterProvider implements AbstractRegisterProvider {
	
	public static final AbstractRegisterProvider DEFAULT = new RegisterProvider();
	
	private final BlockDeferredRegister block;
	private final CommonDeferredRegister<Fluid> fluid;
	private final CommonDeferredRegister<Item> item;
	
	protected RegisterProvider() {
		this(UsefulResourcesMod.MODID);
	}
	
	protected RegisterProvider(String modid) {
		block = BlockDeferredRegister.create(modid);
		fluid = CommonDeferredRegister.create(ForgeRegistries.FLUIDS, modid);
		item = CommonDeferredRegister.create(ForgeRegistries.ITEMS, modid);
	}
	
	@Override
	public BlockDeferredRegister getBlockRegister() {
		return block;
	}
	
	@Override
	public CommonDeferredRegister<Fluid> getFluidRegister() {
		return fluid;
	}
	
	@Override
	public CommonDeferredRegister<Item> getItemRegister() {
		return item;
	}
	
	@Override
	public void register(IEventBus bus) {
		block.register(bus);
		fluid.register(bus);
		item.register(bus);
	}
	
}
