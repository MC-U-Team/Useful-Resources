package info.u_team.useful_resources.api.list;

public enum ListType {
	
	BLACKLIST("blacklist"),
	WHITELIST("whitelist");
	
	private final String name;
	
	private ListType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static ListType byName(String name) {
		if ("whitelist".equals(name)) {
			return WHITELIST;
		}
		return BLACKLIST;
	}
}