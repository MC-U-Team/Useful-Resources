package info.u_team.useful_resources.resource.common;

public class CommonResourceBuilder {
	
	public static BasicOreResourceBuilder basicOre(String name, int color) {
		return new BasicOreResourceBuilder(name, color);
	}
	
}
