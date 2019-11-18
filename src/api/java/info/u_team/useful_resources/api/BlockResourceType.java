package info.u_team.useful_resources.api;

public enum BlockResourceType implements IResourceType {
	
	ORE("ore"),
	NETHER_ORE("nether_ore"),
	BLOCK("block");
	
	private final String name;
	
	private BlockResourceType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
