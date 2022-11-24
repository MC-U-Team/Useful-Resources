package info.u_team.useful_resources.api.resource;

public interface AbstractResourceDataGenInfo {
	
	static AbstractResourceDataGenInfo empty() {
		return new AbstractResourceDataGenInfo() {
		};
	}
}
