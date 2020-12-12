package info.u_team.useful_resources.api.resource.data;

public enum ResourceDataType implements IResourceDataType {
	
	INGOT("ingot"),
	GEM("gem"),
	DUST("dust"),
	BLOCK("block");
	
	private final String name;
	
	private ResourceDataType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}