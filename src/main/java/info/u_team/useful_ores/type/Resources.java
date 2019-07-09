package info.u_team.useful_ores.type;

import java.util.function.Supplier;

import info.u_team.useful_ores.api.*;
import info.u_team.useful_ores.config.CommonConfig;
import net.minecraft.util.IStringSerializable;

public enum Resources implements IResource, IStringSerializable {
	
	COPPER("copper", () -> CommonConfig	.getInstance().copper);
	
	private final String name;
	private final Supplier<IResourceConfig> config;
	
	private Resources(String name, Supplier<IResourceConfig> config) {
		this.name = name;
		this.config = config;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public IResourceConfig getConfig() {
		return config.get();
	}
	
}
