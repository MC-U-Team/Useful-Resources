package info.u_team.useful_resources.type;

import static info.u_team.useful_resources.config.ResourceGenerationConfig.*;
import static info.u_team.useful_resources.type.ResourceBlockTypes.*;

import java.util.*;

import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.resource.Resource;
import info.u_team.useful_resources.resource.Resource.Builder;

public class Resources {
	
	private static final List<IResource> VALUES = new ArrayList<>();
	
	public static final Resource COPPER = register(new Builder("copper", 3F, 1).setGeneration(ORE, createRangeOverworld(9, 20, 0, 0, 64)).setGeneration(NETHER_ORE, createRangeNether(9, 10, 10, 20, 128)));
	public static final Resource TIN = register(new Builder("tin", 3F, 1).setGeneration(ORE, createRangeOverworld(9, 20, 0, 0, 64)).setGeneration(NETHER_ORE, createRangeNether(9, 10, 10, 20, 128)));
	public static final Resource ALUMINUM = register(new Builder("aluminum", 2.6F, 1).setGeneration(ORE, createRangeOverworld(9, 20, 0, 0, 64)).setGeneration(NETHER_ORE, createRangeNether(9, 10, 10, 20, 128)));
	public static final Resource SILVER = register(new Builder("silver", 4F, 2).setGeneration(ORE, createRangeOverworld(9, 2, 0, 0, 32)).setGeneration(NETHER_ORE, createRangeNether(9, 3, 10, 20, 128)));
	public static final Resource LEAD = register(new Builder("lead", 4F, 2).setGeneration(ORE, createRangeOverworld(10, 3, 0, 0, 32)).setGeneration(NETHER_ORE, createRangeNether(9, 3, 10, 20, 128)));
	
	private static Resource register(Builder builder) {
		Resource resource = builder.build();
		VALUES.add(resource);
		return resource;
	}
	
	public static List<IResource> getValues() {
		return Collections.unmodifiableList(VALUES);
	}
	
}
