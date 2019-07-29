package info.u_team.useful_resources.api.resource.config.generation;

public enum GenerationType {
	COUNT_RANGE("count_range"),
	COUNT_DEPTH_AVERAGE("count_depth_average");
	
	private final String name;
	
	private GenerationType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static GenerationType byName(String name) {
		if ("count_range".equals(name)) {
			return COUNT_RANGE;
		}
		if ("count_depth_average".equals(name)) {
			return COUNT_DEPTH_AVERAGE;
		}
		return null;
	}
}