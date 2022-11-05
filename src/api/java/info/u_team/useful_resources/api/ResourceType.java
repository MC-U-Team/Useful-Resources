package info.u_team.useful_resources.api;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface ResourceType<T> {
	
	String getName();
	
	ResourceKey<? extends Registry<T>> getRegistry();
	
	ResourceCreator<T> getCreator();
	
}
