package info.u_team.useful_resources.data.util;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.u_team_core.util.CastUtil;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.type.IResourceType;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LanguageGenerationDecider {
	
	public static LanguageGenerationDecider create() {
		return new LanguageGenerationDecider();
	}
	
	private final Map<IResourceType<?>, LanguageGenerationConsumer<?>> map;
	
	protected LanguageGenerationDecider() {
		map = new LinkedHashMap<>();
	}
	
	public <T extends IForgeRegistryEntry<T>> void addSpecial(IResourceType<T> type, LanguageGenerationConsumerShort<T> consumer) {
		map.put(type, (IResource ignored1, IResourceType<?> ignored2, T entry, String name, IDataGeneratorConfigurator ignored3) -> consumer.accept(entry, name));
	}
	
	public void addSpecialBlock(IResourceType<Block> type, LanguageGenerationConsumerShort<Block> consumer) {
		addSpecial(type, consumer);
	}
	
	public void addSpecialFluid(IResourceType<Fluid> type, LanguageGenerationConsumerShort<Fluid> consumer) {
		addSpecial(type, consumer);
	}
	
	public void addSpecialItem(IResourceType<Item> type, LanguageGenerationConsumerShort<Item> consumer) {
		addSpecial(type, consumer);
	}
	
	public void addSpecial(IResourceType<?> type, LanguageGenerationConsumer<?> consumer) {
		map.put(type, consumer);
	}
	
	public void addSpecialBlock(IResourceType<Block> type, LanguageGenerationConsumer<Block> consumer) {
		addSpecial(type, consumer);
	}
	
	public void addSpecialFluid(IResourceType<Fluid> type, LanguageGenerationConsumer<Fluid> consumer) {
		addSpecial(type, consumer);
	}
	
	public void addSpecialItem(IResourceType<Item> type, LanguageGenerationConsumer<Item> consumer) {
		addSpecial(type, consumer);
	}
	
	public <T extends IForgeRegistryEntry<T>> void generate(IResource resource, IResourceType<?> type, T entry, String name, IDataGeneratorConfigurator configurator, Consumer<T> baseName) {
		final LanguageGenerationConsumer<?> consumer = map.get(type);
		if (consumer != null) {
			consumer.accept(resource, type, CastUtil.uncheckedCast(entry), name, configurator);
		} else {
			baseName.accept(entry);
		}
	}
	
	@FunctionalInterface
	public interface LanguageGenerationConsumer<T extends IForgeRegistryEntry<T>> {
		
		void accept(IResource resource, IResourceType<?> type, T entry, String name, IDataGeneratorConfigurator configurator);
		
	}
	
	@FunctionalInterface
	public interface LanguageGenerationConsumerShort<T extends IForgeRegistryEntry<T>> {
		
		void accept(T entry, String name);
		
	}
	
}
