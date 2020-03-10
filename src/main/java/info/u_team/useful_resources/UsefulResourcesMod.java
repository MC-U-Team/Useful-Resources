package info.u_team.useful_resources;

import java.util.Arrays;

import com.google.gson.JsonElement;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;

import info.u_team.useful_resources.api.list.ListType;
import info.u_team.useful_resources.api.worldgen.*;
import info.u_team.useful_resources.config.CommonConfig;
import info.u_team.useful_resources.resources.Resources;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.*;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(UsefulResourcesMod.MODID)
@EventBusSubscriber(bus = Bus.MOD)
public class UsefulResourcesMod {
	
	public static final String MODID = "usefulresources";
	
	public UsefulResourcesMod() {
		ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfig.CONFIG);
		Resources.register();
	}
	
	@SubscribeEvent
	public static void common(FMLCommonSetupEvent event) {
		
		System.out.println("__________________________________________________________");
		
		final WorldGenFeature worldGen = new WorldGenFeature(new CategoryTypeList(ListType.BLACKLIST, Arrays.asList(Category.NETHER, Category.THEEND)), new BiomeTypeList(ListType.WHITELIST, Arrays.asList(Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU)), Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128))));
		
		final INBT nbt = worldGen.serialize(NBTDynamicOps.INSTANCE).getValue();
		final JsonElement json = worldGen.serialize(JsonOps.INSTANCE).getValue();
		
		System.out.println(nbt);
		System.out.println(json);
		
		final WorldGenFeature worldGenFromNBT = WorldGenFeature.deserialize(new Dynamic<>(NBTDynamicOps.INSTANCE, nbt));
		final WorldGenFeature worldGenFromJson = WorldGenFeature.deserialize(new Dynamic<>(JsonOps.INSTANCE, json));
		
		System.out.println("YOLO");
	}
	
}
