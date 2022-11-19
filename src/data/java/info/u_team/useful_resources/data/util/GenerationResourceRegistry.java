package info.u_team.useful_resources.data.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;

import info.u_team.u_team_core.util.TriConsumer;
import info.u_team.useful_resources.api.registry.ExistingRegistryEntry;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractResource;
import info.u_team.useful_resources.api.resource.AbstractResourceEntries;
import info.u_team.useful_resources.api.resource.AbstractResourceType;
import info.u_team.useful_resources.api.resource.ResourceRegistry;
import net.minecraft.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class GenerationResourceRegistry {
	
	static final Comparator<AbstractResource> COMPARATOR = (a, b) -> a.getName().compareTo(b.getName());
	
	static final Set<AbstractResource> RESOURCES = Util.make(new TreeSet<>(COMPARATOR), list -> ResourceRegistry.forEach(list::add));
	
	public static Set<AbstractResource> getResources() {
		return Collections.unmodifiableSet(RESOURCES);
	}
	
	public static void forEach(Consumer<? super AbstractResource> consumer) {
		RESOURCES.forEach(consumer);
	}
	
	public static void forEachBlock(TriConsumer<? super AbstractResource, AbstractResourceType<Block>, Block> consumer) {
		iterate(AbstractResourceEntries::getBlocks, false, consumer);
	}
	
	public static void forEachFluid(TriConsumer<? super AbstractResource, AbstractResourceType<Fluid>, Fluid> consumer) {
		iterate(AbstractResourceEntries::getFluids, false, consumer);
	}
	
	public static void forEachItem(TriConsumer<? super AbstractResource, AbstractResourceType<Item>, Item> consumer) {
		iterate(AbstractResourceEntries::getItems, false, consumer);
	}
	
	private static <T> void iterate(Function<AbstractResourceEntries, Map<ResourceTypeKey<T>, RegistryEntry<? extends T>>> function, boolean withExisting, TriConsumer<? super AbstractResource, AbstractResourceType<T>, T> consumer) {
		forEach(resource -> {
			function.apply(resource.getEntries()).forEach((type, entry) -> {
				if (!(entry instanceof ExistingRegistryEntry<? extends T>) || withExisting) {
					consumer.accept(resource, type.getType(), entry.get());
				}
			});
		});
	}
	
}
