package info.u_team.useful_resources.api.worldgen;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.list.*;
import net.minecraft.world.biome.Biome.Category;

public class CategoryTypeList extends TypeList<Category> {
	
	public static final Codec<CategoryTypeList> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				ListType.CODEC.fieldOf("type").forGetter(TypeList::getType), //
				Codec.list(Category.CODEC).fieldOf("entries").forGetter(TypeList::getList) //
		).apply(instance, CategoryTypeList::new);
	});
	
	public CategoryTypeList(ListType type, List<Category> list) {
		super(type, list);
	}
	
}
