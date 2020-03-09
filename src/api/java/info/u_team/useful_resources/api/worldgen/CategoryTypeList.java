package info.u_team.useful_resources.api.worldgen;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import info.u_team.useful_resources.api.list.*;
import net.minecraft.world.biome.Biome.Category;

public class CategoryTypeList extends TypeList<Category> {
	
	public CategoryTypeList(ListType type, List<Category> list) {
		super(type, list);
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, ops.createMap(ImmutableMap.of(ops.createString("type"), ops.createString(getType().getName()), ops.createString("entries"), ops.createList(getList().stream().map(entry -> ops.createString(entry.getName()))))));
	}
	
	public static <T> CategoryTypeList deserialize(Dynamic<T> ops) {
		return new CategoryTypeList(ListType.byName(ops.get("type").asString("")), ops.get("entries").asStream().map(entry -> entry.asString("")).filter(Category.BY_NAME::containsKey).map(Category.BY_NAME::get).collect(Collectors.toList()));
	}
	
}
