package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.u_team_core.block.UFluidBlock;
import info.u_team.u_team_core.item.UBucketItem;
import info.u_team.u_team_core.item.armor.ArmorSet;
import info.u_team.u_team_core.item.tool.*;
import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.material.ColoredArmorSetCreator;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.util.TriConsumer;
import info.u_team.useful_resources.block.*;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import info.u_team.useful_resources.item.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.RegistryObject;

public class CommonResourceBuilder {
	
	public static IResourceFeatureBuilder createBasicBlock(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return basicBuilder((name, provider, feature) -> {
			feature.add(type, provider.getBlockRegister().register(basicName(name, type), () -> new BasicBlock(rarity, harvestLevel, hardness, resistance)));
		});
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return createOre(type, rarity, harvestLevel, hardness, resistance, random -> 0);
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		return basicBuilder((name, feature) -> {
			feature.add(type, new OreBlock(basicName(name, type), rarity, harvestLevel, hardness, resistance, experienceDrop));
		});
	}
	
	public static IResourceFeatureBuilder createMoltenFluid(int color) {
		return createMoltenFluid(FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).overlay(new ResourceLocation("block/water_overlay")).temperature(1300).color(color));
	}
	
	public static IResourceFeatureBuilder createMoltenFluid(FluidAttributes.Builder builder) {
		return basicBuilder((name, feature) -> {
			final AtomicReference<USourceFluid> sourceFluidReference = new AtomicReference<>();
			final AtomicReference<UFlowingFluid> flowingFluidReference = new AtomicReference<>();
			final AtomicReference<UFluidBlock> fluidBlockReference = new AtomicReference<>();
			final AtomicReference<UBucketItem> bucketItemReference = new AtomicReference<>();
			
			final ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(sourceFluidReference::get, flowingFluidReference::get, builder).block(fluidBlockReference::get).bucket(bucketItemReference::get);
			
			sourceFluidReference.set(feature.add(FluidResourceType.MOLTEN, new USourceFluid(basicName(name, FluidResourceType.MOLTEN), properties)));
			flowingFluidReference.set(feature.add(FluidResourceType.MOLTEN_FLOWING, new UFlowingFluid(basicName(name, FluidResourceType.MOLTEN_FLOWING), properties)));
			fluidBlockReference.set(feature.add(BlockResourceType.MOLTEN_FLUID, new UFluidBlock(basicName(name, BlockResourceType.MOLTEN_FLUID), Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops(), sourceFluidReference::get)));
			bucketItemReference.set(feature.add(ItemResourceType.MOLTEN_BUCKET, new UBucketItem(basicName(name, ItemResourceType.MOLTEN_BUCKET), new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(UsefulResourcesItemGroups.GROUP), sourceFluidReference::get)));
		});
	}
	
	public static IResourceFeatureBuilder createBasicItem(ItemResourceType type, Rarity rarity) {
		return basicBuilder((name, feature) -> {
			feature.add(type, new BasicItem(basicName(name, type), rarity));
		});
	}
	
	public static IResourceFeatureBuilder createTools(Rarity rarity, IToolMaterial material) {
		return basicBuilder((name, feature) -> {
			final ToolSet toolSet = ToolSetCreator.create(name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.AXE, toolSet.getAxe());
			feature.add(ItemResourceType.HOE, toolSet.getHoe());
			feature.add(ItemResourceType.PICKAXE, toolSet.getPickaxe());
			feature.add(ItemResourceType.SHOVEL, toolSet.getShovel());
			feature.add(ItemResourceType.SWORD, toolSet.getSword());
		});
	}
	
	public static IResourceFeatureBuilder createArmor(Rarity rarity, IArmorMaterial material) {
		return basicBuilder((name, feature) -> {
			final ArmorSet armorSet = ColoredArmorSetCreator.create(name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.HELMET, armorSet.getHelmet());
			feature.add(ItemResourceType.CHESTPLATE, armorSet.getChestplate());
			feature.add(ItemResourceType.LEGGINGS, armorSet.getLeggings());
			feature.add(ItemResourceType.BOOTS, armorSet.getBoots());
		});
	}
	
	public static IResourceFeatureBuilder createHorseArmor(Rarity rarity, int armorPoints) {
		return basicBuilder((name, feature) -> {
			feature.add(ItemResourceType.HORSE_ARMOR, new BasicHorseArmorItem(name, rarity, armorPoints));
		});
	}
	
	public static IResourceFeatureBuilder addExistingBlock(BlockResourceType type, Block block) {
		return basicBuilder((name, feature) -> {
			feature.addExisting(type, block);
		});
	}
	
	public static IResourceFeatureBuilder addExistingFluid(FluidResourceType type, Fluid fluid) {
		return basicBuilder((name, feature) -> {
			feature.addExisting(type, fluid);
		});
	}
	
	public static IResourceFeatureBuilder addExistingItem(ItemResourceType type, Item item) {
		return basicBuilder((name, feature) -> {
			feature.addExisting(type, item);
		});
	}
	
	private static String basicName(String name, IResourceType<?> type) {
		return name + "_" + type.getName();
	}
	
	private static IResourceFeatureBuilder basicBuilder(TriConsumer<String, IDeferredRegisterProvider, ResourceFeature> consumer) {
		return (name, provider) -> {
			final ResourceFeature feature = new ResourceFeature(name, provider);
			consumer.accept(name, provider, feature);
			return feature;
		};
	}
	
	private static class ResourceFeature implements IResourceFeature {
		
		private final String name;
		private final IDeferredRegisterProvider provider;
		
		private final Map<BlockResourceType, RegistryEntry<? extends Block>> blocks;
		private final Map<FluidResourceType, RegistryEntry<? extends Fluid>> fluids;
		private final Map<ItemResourceType, RegistryEntry<? extends Item>> items;
		
		private final List<RegistryEntry<? extends Block>> registryBlocks;
		private final List<RegistryEntry<? extends Fluid>> registryFluids;
		private final List<RegistryEntry<? extends Item>> registryItems;
		
		private ResourceFeature(String name, IDeferredRegisterProvider provider) {
			this.name = name;
			this.provider = provider;
			blocks = new EnumMap<>(BlockResourceType.class);
			fluids = new EnumMap<>(FluidResourceType.class);
			items = new EnumMap<>(ItemResourceType.class);
			registryBlocks = new ArrayList<>();
			registryFluids = new ArrayList<>();
			registryItems = new ArrayList<>();
		}
		
		private <T extends Block & IBlockItemProvider> RegistryEntry<? extends T> register(BlockResourceType type, Supplier<? extends T> supplier) {
			final BlockRegistryObject<? extends T, BlockItem> registryObject = provider.getBlockRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			blocks.put(type, entry);
			registryBlocks.add(entry);
			return entry;
		}
		
		private <T extends Fluid> RegistryEntry<? extends T> register(FluidResourceType type, Supplier<? extends T> supplier) {
			final RegistryObject<? extends T> registryObject = provider.getFluidRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			fluids.put(type, entry);
			registryFluids.add(entry);
			return entry;
		}
		
		private <T extends Item> RegistryEntry<? extends T> register(ItemResourceType type, Supplier<? extends T> supplier) {
			final RegistryObject<? extends T> registryObject = provider.getItemRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			items.put(type, entry);
			registryItems.add(entry);
			return entry;
		}
		
		private <T extends RegistryEntry<Block>> T addExisting(BlockResourceType type, T block) {
			blocks.put(type, block);
			return block;
		}
		
		private <T extends RegistryEntry<Fluid>> T addExisting(FluidResourceType type, T fluid) {
			fluids.put(type, fluid);
			return fluid;
		}
		
		private <T extends RegistryEntry<Item>> T addExisting(ItemResourceType type, T item) {
			items.put(type, item);
			return item;
		}
		
		@Override
		public Map<BlockResourceType, RegistryEntry<? extends Block>> getBlocks() {
			return blocks;
		}
		
		@Override
		public Map<FluidResourceType, RegistryEntry<? extends Fluid>> getFluids() {
			return fluids;
		}
		
		@Override
		public Map<ItemResourceType, RegistryEntry<? extends Item>> getItems() {
			return items;
		}
		
		@Override
		public List<RegistryEntry<? extends Block>> getRegistryBlocks() {
			return registryBlocks;
		}
		
		@Override
		public List<RegistryEntry<? extends Fluid>> getRegistryFluids() {
			return registryFluids;
		}
		
		@Override
		public List<RegistryEntry<? extends Item>> getRegistryItems() {
			return registryItems;
		}
	}
}
