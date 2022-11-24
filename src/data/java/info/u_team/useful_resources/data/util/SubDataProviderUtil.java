package info.u_team.useful_resources.data.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;

import org.objectweb.asm.Type;

import info.u_team.u_team_core.util.CastUtil;
import info.u_team.u_team_core.util.annotation.AnnotationUtil;
import info.u_team.useful_resources.UsefulResourcesMod;
import net.minecraft.data.DataProvider;
import net.minecraftforge.forgespi.language.ModFileScanData.AnnotationData;

public class SubDataProviderUtil {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface RegisterSubDataProvider {
		
		Class<?> value();
	}
	
	public static interface SubDataProvider<T extends DataProvider> {
		
		void register(T provider);
		
	}
	
	public static <T extends DataProvider> void registerSubProvider(T provider) {
		for (final AnnotationData data : AnnotationUtil.getAnnotations(UsefulResourcesMod.MODID, Type.getType(RegisterSubDataProvider.class))) {
			try {
				final Type providerType = CastUtil.uncheckedCast(data.annotationData().get("value"));
				if (providerType.equals(Type.getType(provider.getClass()))) {
					final Class<SubDataProvider<T>> subProviderClass = CastUtil.uncheckedCast(Class.forName(data.memberName()).asSubclass(SubDataProvider.class));
					subProviderClass.getConstructor().newInstance().register(provider);
				}
			} catch (LinkageError | ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
				throw new RuntimeException("Failed to register sub provider with annotation on class " + data.memberName(), ex);
			}
		}
	}
	
}
