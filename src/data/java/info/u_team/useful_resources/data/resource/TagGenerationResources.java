package info.u_team.useful_resources.data.resource;

import java.util.*;
import java.util.function.Consumer;

import info.u_team.u_team_core.util.TriConsumer;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.resources.Resources;
import info.u_team.useful_resources.util.FakeNameResource;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Util;

public class TagGenerationResources {
	
	static final Set<IResource> RESOURCES = Util.make(new TreeSet<>(GenerationResources.COMPARATOR), list -> GenerationResources.forEach(list::add));
	
	static {
		RESOURCES.add(new FakeNameResource("aluminium", Resources.ALUMINUM));
		RESOURCES.add(new FakeNameResource("uranium", Resources.URANIUM_238));
	}
	
	public static Set<IResource> getResources() {
		return Collections.unmodifiableSet(RESOURCES);
	}
	
	public static void forEach(Consumer<? super IResource> consumer) {
		RESOURCES.forEach(consumer);
	}
	
	public static void forEachBlock(TriConsumer<? super IResource, BlockResourceType, Block> consumer) {
		RESOURCES.stream().forEach(resource -> resource.iterateRegistryBlocks((type, block) -> consumer.accept(resource, type, block)));
	}
	
	public static void forEachFluid(TriConsumer<? super IResource, FluidResourceType, Fluid> consumer) {
		RESOURCES.stream().forEach(resource -> resource.iterateRegistryFluids((type, fluid) -> consumer.accept(resource, type, fluid)));
	}
	
	public static void forEachItem(TriConsumer<? super IResource, ItemResourceType, Item> consumer) {
		RESOURCES.stream().forEach(resource -> resource.iterateRegistryItems((type, item) -> consumer.accept(resource, type, item)));
	}
}
