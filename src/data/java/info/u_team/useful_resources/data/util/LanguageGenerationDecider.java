package info.u_team.useful_resources.data.util;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.u_team_core.util.CastUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LanguageGenerationDecider {
	
	public static LanguageGenerationDecider create() {
		return new LanguageGenerationDecider();
	}
	
	private final Map<IResourceType<?>, LanguageGenerationConsumer<?>> map;
	
	protected LanguageGenerationDecider() {
		map = new LinkedHashMap<>();
	}
	
	public void addSpecial(IResourceType<?> type, LanguageGenerationConsumer<?> consumer) {
		map.put(type, consumer);
	}
	
	public <T extends IForgeRegistryEntry<T>> void generate(IResource resource, IResourceType<T> type, T entry, String name, IDataGeneratorConfigurator configurator, Consumer<T> baseName) {
		final LanguageGenerationConsumer<?> consumer = map.get(type);
		if (consumer != null) {
			consumer.accept(resource, CastUtil.uncheckedCast(type), CastUtil.uncheckedCast(entry), name, configurator);
		} else {
			baseName.accept(entry);
		}
	}
	
	@FunctionalInterface
	public interface LanguageGenerationConsumer<T extends IForgeRegistryEntry<T>> {
		
		void accept(IResource resource, IResourceType<T> type, T entry, String name, IDataGeneratorConfigurator configurator);
		
	}
	
}
