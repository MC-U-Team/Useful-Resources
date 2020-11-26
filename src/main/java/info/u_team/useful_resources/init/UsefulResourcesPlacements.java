package info.u_team.useful_resources.init;

import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.placement.OnlyEndIslandsPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class UsefulResourcesPlacements {
	
	public static final CommonDeferredRegister<Placement<?>> PLACEMENTS = CommonDeferredRegister.create(ForgeRegistries.DECORATORS, UsefulResourcesMod.MODID);
	
	public static final RegistryObject<OnlyEndIslandsPlacement> ONLY_END_ISLANDS = PLACEMENTS.register("only_end_islands", OnlyEndIslandsPlacement::new);
	
	public static void registerMod(IEventBus bus) {
		PLACEMENTS.register(bus);
	}
	
}
