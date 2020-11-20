package info.u_team.useful_resources.api.worldgen;

import java.util.*;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import info.u_team.useful_resources.api.list.*;
import net.minecraft.util.*;
import net.minecraft.world.biome.Biome;

public class BiomeTypeList extends TypeList<ResourceLocation, BiomeTypeList> {
	
	public static final Codec<BiomeTypeList> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group( //
				ListType.CODEC.fieldOf("type").forGetter(TypeList::getType), //
				Codec.list(ResourceLocation.CODEC).fieldOf("entries").forGetter(TypeList::getList) //
		).apply(instance, BiomeTypeList::new);
	});
	
	public static BiomeTypeList create(ListType type) {
		return new BiomeTypeList(type, new ArrayList<>());
	}
	
	private BiomeTypeList(ListType type, List<ResourceLocation> biomes) {
		super(type, biomes);
	}
	
	public BiomeTypeList add(RegistryKey<Biome> biome) {
		return add(biome.getLocation());
	}
}
