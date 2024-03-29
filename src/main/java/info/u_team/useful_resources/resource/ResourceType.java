package info.u_team.useful_resources.resource;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class ResourceType<T> implements AbstractResourceType<T> {
	
	private final String name;
	private final UnaryOperator<String> defaultRegistryNameOperator;
	private final String tagName;
	
	private final Function<ResourceLocation, TagKey<T>> tagFunction;
	
	protected ResourceType(String name, UnaryOperator<String> defaultRegistryNameOperator, String tagName, Function<ResourceLocation, TagKey<T>> tagFunction) {
		this.name = name;
		this.defaultRegistryNameOperator = defaultRegistryNameOperator;
		this.tagName = tagName;
		this.tagFunction = tagFunction;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDefaultRegistryName(String resourceName) {
		return defaultRegistryNameOperator.apply(resourceName);
	}
	
	@Override
	public boolean hasUnifyTag() {
		return tagName != null;
	}
	
	@Override
	public TagKey<T> getUnifyTag() {
		if (tagName == null) {
			return null;
		}
		return tagFunction.apply(new ResourceLocation("forge", tagName));
	}
	
	@Override
	public boolean hasTag() {
		return tagName != null;
	}
	
	@Override
	public TagKey<T> getTag(AbstractResource resource) {
		if (tagName == null) {
			return null;
		}
		return tagFunction.apply(new ResourceLocation("forge", tagName + "/" + resource.getName()));
	}
	
}
