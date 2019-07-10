package info.u_team.useful_resources.api;

import java.util.function.Supplier;

public interface IResource {
	
	String getName();
	
	Supplier<IResourceConfig> getConfig();
	
	IResourceBlocks getBlocks();
	
	IResourceItems getItems();
	
}
