package info.u_team.useful_resources.api.resource.data;

import info.u_team.useful_resources.api.type.*;
import net.minecraft.util.IItemProvider;

public enum ResourceDataType implements IResourceDataType {
	
	INGOT("ingot", ItemResourceType.INGOT, ItemResourceType.NUGGET),
	GEM("gem", ItemResourceType.GEM, ItemResourceType.PIECE),
	DUST("dust", ItemResourceType.DUST, ItemResourceType.PIECE),
	BLOCK("block", BlockResourceType.BLOCK, ItemResourceType.PIECE);
	
	private final String name;
	private final IResourceType<? extends IItemProvider> normalType;
	private final IResourceType<? extends IItemProvider> tinyType;
	
	private ResourceDataType(String name, IResourceType<? extends IItemProvider> normalType, IResourceType<? extends IItemProvider> tinyType) {
		this.name = name;
		this.normalType = normalType;
		this.tinyType = tinyType;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public IResourceType<? extends IItemProvider> getNormalResourceType() {
		return normalType;
	}
	
	@Override
	public IResourceType<? extends IItemProvider> getTinyResourceType() {
		return tinyType;
	}
}