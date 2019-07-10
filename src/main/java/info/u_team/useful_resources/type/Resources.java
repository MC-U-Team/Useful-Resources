package info.u_team.useful_resources.type;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import info.u_team.useful_resources.api.*;
import info.u_team.useful_resources.api.IGeneratable.*;
import info.u_team.useful_resources.config.*;
import info.u_team.useful_resources.resource.*;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.placement.CountRangeConfig;

public enum Resources implements IResource, IStringSerializable {
	
	COPPER("copper", new DefaultConfig(3F, 3F, 3F, 3F, 3F, 6F, new GeneratableConfig(true, ListType.BLACKLIST, new Category[] { Category.NETHER, Category.THEEND }, ListType.BLACKLIST, new Biome[] {}, 9, GenerationConfig.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 64), null), new GeneratableConfig(true, ListType.WHITELIST, new Category[] { Category.NETHER }, ListType.BLACKLIST, new Biome[] {}, 9, GenerationConfig.COUNT_RANGE, new CountRangeConfig(16, 10, 20, 128), null)));
	
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
