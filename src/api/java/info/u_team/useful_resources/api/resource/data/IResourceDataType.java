package info.u_team.useful_resources.api.resource.data;

import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.util.IItemProvider;

public interface IResourceDataType {
	
	String getName();
	
	IResourceType<? extends IItemProvider> getNormalResourceType();
	
	IResourceType<? extends IItemProvider> getTinyResourceType();
	
}
