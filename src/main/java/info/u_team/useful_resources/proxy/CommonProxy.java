package info.u_team.useful_resources.proxy;

import info.u_team.u_team_core.api.IModProxy;
import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.config.CommonConfig;
import info.u_team.useful_resources.init.UsefulResourcesWorldGeneration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG, UsefulResourcesMod.MODID + "/common.toml");
	}
	
	@Override
	public void setup() {
		UsefulResourcesWorldGeneration.setup();
	}
	
	@Override
	public void complete() {
	}
	
}
