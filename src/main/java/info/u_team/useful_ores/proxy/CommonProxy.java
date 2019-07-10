package info.u_team.useful_ores.proxy;

import info.u_team.u_team_core.api.IModProxy;
import info.u_team.useful_ores.UsefulOresMod;
import info.u_team.useful_ores.config.CommonConfig;
import info.u_team.useful_ores.init.UsefulOresWorldGeneration;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class CommonProxy implements IModProxy {
	
	@Override
	public void construct() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG, UsefulOresMod.MODID + "/common.toml");
	}
	
	@Override
	public void setup() {
		UsefulOresWorldGeneration.setup();
	}
	
	@Override
	public void complete() {
	}
	
}
