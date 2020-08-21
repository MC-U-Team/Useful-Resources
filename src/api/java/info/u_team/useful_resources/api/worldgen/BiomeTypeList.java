package info.u_team.useful_resources.api.worldgen;
/*
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.*;

import info.u_team.useful_resources.api.list.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.*;
import net.minecraft.world.biome.*;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeTypeList extends TypeList<Biome> {
	
	public BiomeTypeList(ListType type, List<Biome> list) {
		super(type, list);
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("type"), ops.createString(getType().getName()), ops.createString("entries"), ops.createList(getList().stream().map(entry -> ops.createString(entry.getRegistryName().toString()))))));
	}
	
	public static <T> BiomeTypeList deserialize(Dynamic<T> ops) {
		return new BiomeTypeList(ListType.byName(ops.get("type").asString("")), ops.get("entries").asStream().map(entry -> ResourceLocation.tryCreate(entry.asString(""))).filter(ForgeRegistries.BIOMES::containsKey).map(ForgeRegistries.BIOMES::getValue).collect(Collectors.toList()));
	}
	
}*/
