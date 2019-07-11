package info.u_team.useful_resources.type;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.config.*;
import info.u_team.useful_resources.resource.*;
import net.minecraft.util.IStringSerializable;

public enum Resources implements IResource, IStringSerializable {
	
	COPPER("copper", new DefaultConfig(3F, 3F, 3F, 3F, 3F, 6F, GeneratableConfig.createRangeOverworld(9, 20, 0, 0, 64), GeneratableConfig.createRangeNether(9, 10, 10, 20, 128))),
	TIN("tin", new DefaultConfig(3F, 3F, 3F, 3F, 3F, 6F, GeneratableConfig.createRangeOverworld(9, 20, 0, 0, 64), GeneratableConfig.createRangeNether(9, 10, 10, 20, 128))),
	ALUMINUM("aluminum", new DefaultConfig(2.6F, 3F, 2.5F, 3F, 3F, 6F, GeneratableConfig.createRangeOverworld(9, 20, 0, 0, 64), GeneratableConfig.createRangeNether(9, 10, 10, 20, 128))),
	SILVER("silver", new DefaultConfig(4F, 4F, 4F, 3F, 3F, 6F, GeneratableConfig.createRangeOverworld(9, 2, 0, 0, 32), GeneratableConfig.createRangeNether(9, 3, 10, 20, 128))),
	LEAD("lead", new DefaultConfig(4F, 4F, 4F, 3F, 3F, 6F, GeneratableConfig.createRangeOverworld(10, 3, 0, 0, 32), GeneratableConfig.createRangeNether(9, 3, 10, 20, 128)));
	
	public static final List<Resources> VALUES = Collections.unmodifiableList(Arrays.stream(values()).collect(Collectors.toList()));
	
	private final String name;
	private final DefaultConfig defaultConfig;
	private final IResourceBlocks blocks;
	private final IResourceItems items;
	
	private IResourceConfig config;
	
	private Resources(String name, DefaultConfig defaultConfig) {
		this.name = name;
		this.defaultConfig = defaultConfig;
		blocks = new BlockResourceSet(this);
		items = new ItemResourceSet(this);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public Supplier<IResourceConfig> getConfig() {
		return () -> config;
	}
	
	@Override
	public IResourceBlocks getBlocks() {
		return blocks;
	}
	
	@Override
	public IResourceItems getItems() {
		return items;
	}
	
	/**
	 * ONLY INTERNAL USE
	 */
	public void setConfig(IResourceConfig config) {
		this.config = config;
	}
	
	/**
	 * ONLY INTERNAL USE
	 */
	public DefaultConfig getDefaultConfig() {
		return defaultConfig;
	}
	
}
