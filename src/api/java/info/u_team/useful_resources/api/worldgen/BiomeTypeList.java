package info.u_team.useful_resources.api.worldgen;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.list.*;
import net.minecraft.util.ResourceLocation;

public class BiomeTypeList extends TypeList<ResourceLocation> {
	
	public static final Codec<BiomeTypeList> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				ListType.CODEC.fieldOf("type").forGetter(TypeList::getType), //
				Codec.list(ResourceLocation.CODEC).fieldOf("entries").forGetter(TypeList::getList) //
		).apply(instance, BiomeTypeList::new);
	});
	
	public BiomeTypeList(ListType type, List<ResourceLocation> biomes) {
		super(type, biomes);
	}
}
