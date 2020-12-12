package info.u_team.useful_resources.api.resource.data;

public enum ResourceType implements IResourceType {
	
	INGOT("ingot"),
	GEM("gem"),
	DUST("dust"),
	BLOCK("block");
	
	private final String name;
	
	private ResourceType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}