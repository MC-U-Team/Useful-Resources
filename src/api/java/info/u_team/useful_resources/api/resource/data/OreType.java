package info.u_team.useful_resources.api.resource.data;

public enum OreType {
	
	INGOT("ingot");
	
	private final String name;
	
	private OreType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
