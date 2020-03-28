package info.u_team.useful_resources.util;

public class ObjectUtil {
	
	public static float getFloat(Object object) {
		if (object instanceof Integer) {
			return (int) object;
		} else if (object instanceof Float) {
			return (float) object;
		} else if (object instanceof Double) {
			return (float) ((double) object);
		} else {
			return 0;
		}
	}
	
	public static double getDouble(Object object) {
		if (object instanceof Integer) {
			return (int) object;
		} else if (object instanceof Float) {
			return (float) object;
		} else if (object instanceof Double) {
			return (double) object;
		} else {
			return 0;
		}
	}
	
	public static boolean getBoolean(Object object) {
		if (object instanceof Boolean) {
			return (boolean) object;
		} else {
			return false;
		}
	}
	
}
