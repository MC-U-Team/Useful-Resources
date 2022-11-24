package info.u_team.useful_resources.api.resource.datagen;

public enum ResourceDataCategory {
	
	COAL_LIKE("coal_like"),
	IRON_LIKE("iron_like"),
	COPPER_LIKE("copper_like"),
	GOLD_LIKE("gold_like"),
	GOLD_NUGGER_LIKE("gold_nugget_like"),
	QUARTZ_LIKE("quartz_like"),
	LAPIS_LIKE("lapis_like"),
	REDSTONE_LIKE("redstone_like"),
	DIAMOND_LIKE("diamond_like"),
	EMERALD_LIKE("emerald_like");
	
	private final String name;
	
	private ResourceDataCategory(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
