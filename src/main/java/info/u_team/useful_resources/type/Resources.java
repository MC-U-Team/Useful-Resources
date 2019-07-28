package info.u_team.useful_resources.type;

import java.util.*;

import info.u_team.useful_resources.type.Resource.Builder;

public class Resources {
	
	public static final Resource COPPER = register(new Builder("copper", 3F, 1));
	public static final Resource TIN = register(new Builder("tin", 3F, 1));
	public static final Resource ALUMINUM = register(new Builder("aluminum", 2.6F, 1));
	public static final Resource SILVER = register(new Builder("silver", 4F, 2));
	public static final Resource LEAD = register(new Builder("lead", 4F, 2));
	
	private static Resource register(Builder builder) {
		Resource resource = builder.build();
		INTERN_VALUES.add(resource);
		return resource;
	}
	
	private static final List<Resource> INTERN_VALUES = new ArrayList<>();
	public static final List<Resource> VALUES = Collections.unmodifiableList(INTERN_VALUES);
	
}
