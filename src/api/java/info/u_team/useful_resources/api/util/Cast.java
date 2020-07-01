package info.u_team.useful_resources.api.util;

public class Cast {
	
	@SuppressWarnings("unchecked")
	public static <T> T uncheckedCast(Object obj) {
		return (T) obj;
	}
	
}
