package info.u_team.useful_resources.resource;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import info.u_team.u_team_core.util.TagUtil;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ResourceTypeBuilder<T> {
	
	public static ResourceTypeBuilder<Block> block(String name) {
		return new ResourceTypeBuilder<>(name, TagUtil::createBlockTag);
	}
	
	public static ResourceTypeBuilder<Fluid> fluid(String name) {
		return new ResourceTypeBuilder<>(name, TagUtil::createFluidTag);
	}
	
	public static ResourceTypeBuilder<Item> item(String name) {
		return new ResourceTypeBuilder<>(name, TagUtil::createItemTag);
	}
	
	private final String name;
	private final Function<ResourceLocation, TagKey<T>> tagFunction;
	
	private UnaryOperator<String> defaultRegistryNameOperator;
	private String tagName;
	
	public ResourceTypeBuilder(String name, Function<ResourceLocation, TagKey<T>> tagFunction) {
		this.name = name;
		this.tagFunction = tagFunction;
		
		defaultRegistryNameOperator = resourceName -> resourceName + "_" + name;
		tagName = name + "s";
	}
	
	public ResourceTypeBuilder<T> defaultRegistryName(String before, String... after) {
		final String joined = Stream.of(after).collect(Collectors.joining());
		return defaultRegistryName(name -> before + "_" + name + (joined.isEmpty() ? "" : "_" + joined));
	}
	
	public ResourceTypeBuilder<T> defaultRegistryName(UnaryOperator<String> defaultRegistryNameOperator) {
		this.defaultRegistryNameOperator = defaultRegistryNameOperator;
		return this;
	}
	
	public ResourceTypeBuilder<T> tagName(String tagName) {
		this.tagName = tagName;
		return this;
	}
	
	public ResourceTypeBuilder<T> noTag() {
		tagName = null;
		return this;
	}
	
	public ResourceTypeKey<T> build() {
		return ResourceTypeKey.create(new ResourceType<>(name, defaultRegistryNameOperator, tagName, tagFunction));
	}
	
}
