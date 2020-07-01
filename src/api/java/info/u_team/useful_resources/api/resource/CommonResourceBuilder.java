package info.u_team.useful_resources.api.resource;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.*;

import info.u_team.u_team_core.api.IToolMaterial;
import info.u_team.u_team_core.api.registry.IBlockItemProvider;
import info.u_team.u_team_core.item.UBucketItem;
import info.u_team.u_team_core.item.armor.ArmorSet;
import info.u_team.u_team_core.item.tool.*;
import info.u_team.u_team_core.util.registry.BlockRegistryObject;
import info.u_team.useful_resources.api.feature.*;
import info.u_team.useful_resources.api.material.ColoredArmorSetCreator;
import info.u_team.useful_resources.api.registry.RegistryEntry;
import info.u_team.useful_resources.api.type.*;
import info.u_team.useful_resources.api.util.TriConsumer;
import info.u_team.useful_resources.block.BasicBlock;
import info.u_team.useful_resources.block.OreBlock;
import info.u_team.useful_resources.init.UsefulResourcesItemGroups;
import info.u_team.useful_resources.item.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid.*;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;
import net.minecraftforge.fml.RegistryObject;

public class CommonResourceBuilder {
	
	public static IResourceFeatureBuilder createBasicBlock(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return basicBuilder((name, provider, feature) -> {
			feature.register(type, () -> new BasicBlock(rarity, harvestLevel, hardness, resistance));
		});
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance) {
		return createOre(type, rarity, harvestLevel, hardness, resistance, random -> 0);
	}
	
	public static IResourceFeatureBuilder createOre(BlockResourceType type, Rarity rarity, int harvestLevel, float hardness, float resistance, Function<Random, Integer> experienceDrop) {
		return basicBuilder((name, provider, feature) -> {
			feature.register(type, () -> new OreBlock(rarity, harvestLevel, hardness, resistance, experienceDrop));
		});
	}
	
	public static IResourceFeatureBuilder createMoltenFluid(int color) {
		return createMoltenFluid(FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow")).overlay(new ResourceLocation("block/water_overlay")).temperature(1300).color(color));
	}
	
	public static IResourceFeatureBuilder createMoltenFluid(FluidAttributes.Builder builder) {
		return basicBuilder((name, provider, feature) -> {
			final AtomicReference<RegistryEntry<? extends Source>> sourceFluidReference = new AtomicReference<>();
			final AtomicReference<RegistryEntry<? extends Flowing>> flowingFluidReference = new AtomicReference<>();
			final AtomicReference<RegistryEntry<? extends FlowingFluidBlock>> fluidBlockReference = new AtomicReference<>();
			final AtomicReference<RegistryEntry<? extends UBucketItem>> bucketItemReference = new AtomicReference<>();
			
			final Properties properties = new Properties(() -> sourceFluidReference.get().get(), () -> flowingFluidReference.get().get(), builder).block(() -> fluidBlockReference.get().get()).bucket(() -> bucketItemReference.get().get());
			
			sourceFluidReference.set(feature.register(FluidResourceType.MOLTEN, () -> new Source(properties)));
			flowingFluidReference.set(feature.register(FluidResourceType.MOLTEN_FLOWING, () -> new Flowing(properties)));
			fluidBlockReference.set(feature.registerBlock(BlockResourceType.MOLTEN_FLUID, () -> new FlowingFluidBlock(() -> sourceFluidReference.get().get(), Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops())));
			bucketItemReference.set(feature.register(ItemResourceType.MOLTEN_BUCKET, () -> new UBucketItem(UsefulResourcesItemGroups.GROUP, new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1), () -> sourceFluidReference.get().get()) {
				
				@Override
				public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
					System.out.println(getFluid().getRegistryName());
					return super.onItemRightClick(world, player, hand);
				}
			}));
		});
	}
	
	public static IResourceFeatureBuilder createBasicItem(ItemResourceType type, Rarity rarity) {
		return basicBuilder((name, provider, feature) -> {
			feature.register(type, () -> new BasicItem(rarity));
		});
	}
	
	public static IResourceFeatureBuilder createTools(Rarity rarity, IToolMaterial material) {
		return basicBuilder((name, provider, feature) -> {
			final ToolSet toolSet = ToolSetCreator.create(provider.getItemRegister(), name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.AXE, RegistryEntry.create(toolSet.getAxe()));
			feature.add(ItemResourceType.HOE, RegistryEntry.create(toolSet.getHoe()));
			feature.add(ItemResourceType.PICKAXE, RegistryEntry.create(toolSet.getPickaxe()));
			feature.add(ItemResourceType.SHOVEL, RegistryEntry.create(toolSet.getShovel()));
			feature.add(ItemResourceType.SWORD, RegistryEntry.create(toolSet.getSword()));
		});
	}
	
	public static IResourceFeatureBuilder createArmor(Rarity rarity, IArmorMaterial material) {
		return basicBuilder((name, provider, feature) -> {
			final ArmorSet armorSet = ColoredArmorSetCreator.create(provider.getItemRegister(), name, UsefulResourcesItemGroups.GROUP, new Item.Properties().rarity(rarity), material);
			feature.add(ItemResourceType.HELMET, RegistryEntry.create(armorSet.getHelmet()));
			feature.add(ItemResourceType.CHESTPLATE, RegistryEntry.create(armorSet.getChestplate()));
			feature.add(ItemResourceType.LEGGINGS, RegistryEntry.create(armorSet.getLeggings()));
			feature.add(ItemResourceType.BOOTS, RegistryEntry.create(armorSet.getBoots()));
		});
	}
	
	public static IResourceFeatureBuilder createHorseArmor(Rarity rarity, int armorPoints) {
		return basicBuilder((name, provider, feature) -> {
			feature.register(ItemResourceType.HORSE_ARMOR, () -> new BasicHorseArmorItem(rarity, armorPoints));
		});
	}
	
	public static IResourceFeatureBuilder addExistingBlock(BlockResourceType type, Block block) {
		return basicBuilder((name, provider, feature) -> {
			feature.addExisting(type, RegistryEntry.create(block));
		});
	}
	
	public static IResourceFeatureBuilder addExistingFluid(FluidResourceType type, Fluid fluid) {
		return basicBuilder((name, provider, feature) -> {
			feature.addExisting(type, RegistryEntry.create(fluid));
		});
	}
	
	public static IResourceFeatureBuilder addExistingItem(ItemResourceType type, Item item) {
		return basicBuilder((name, provider, feature) -> {
			feature.addExisting(type, RegistryEntry.create(item));
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
	
	protected static class ResourceFeature implements IResourceFeature {
		
		private final String name;
		private final IDeferredRegisterProvider provider;
		
		private final Map<BlockResourceType, RegistryEntry<? extends Block>> blocks;
		private final Map<FluidResourceType, RegistryEntry<? extends Fluid>> fluids;
		private final Map<ItemResourceType, RegistryEntry<? extends Item>> items;
		
		private final List<RegistryEntry<? extends Block>> registryBlocks;
		private final List<RegistryEntry<? extends Fluid>> registryFluids;
		private final List<RegistryEntry<? extends Item>> registryItems;
		
		protected ResourceFeature(String name, IDeferredRegisterProvider provider) {
			this.name = name;
			this.provider = provider;
			blocks = new EnumMap<>(BlockResourceType.class);
			fluids = new EnumMap<>(FluidResourceType.class);
			items = new EnumMap<>(ItemResourceType.class);
			registryBlocks = new ArrayList<>();
			registryFluids = new ArrayList<>();
			registryItems = new ArrayList<>();
		}
		
		protected <T extends Block & IBlockItemProvider> RegistryEntry<? extends T> register(BlockResourceType type, Supplier<? extends T> supplier) {
			final BlockRegistryObject<? extends T, BlockItem> registryObject = provider.getBlockRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			blocks.put(type, entry);
			registryBlocks.add(entry);
			return entry;
		}
		
		protected <T extends Block> RegistryEntry<? extends T> registerBlock(BlockResourceType type, Supplier<? extends T> supplier) {
			final RegistryObject<? extends T> registryObject = provider.getBlockRegister().registerBlock(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			blocks.put(type, entry);
			registryBlocks.add(entry);
			return entry;
		}
		
		protected <T extends Fluid> RegistryEntry<? extends T> register(FluidResourceType type, Supplier<? extends T> supplier) {
			final RegistryObject<? extends T> registryObject = provider.getFluidRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			fluids.put(type, entry);
			registryFluids.add(entry);
			return entry;
		}
		
		protected <T extends Item> RegistryEntry<? extends T> register(ItemResourceType type, Supplier<? extends T> supplier) {
			final RegistryObject<? extends T> registryObject = provider.getItemRegister().register(basicName(name, type), supplier);
			final RegistryEntry<? extends T> entry = RegistryEntry.create(registryObject);
			items.put(type, entry);
			registryItems.add(entry);
			return entry;
		}
		
		protected <T extends Block> RegistryEntry<T> add(BlockResourceType type, RegistryEntry<T> entry) {
			blocks.put(type, entry);
			registryBlocks.add(entry);
			return entry;
		}
		
		protected <T extends Fluid> RegistryEntry<T> add(FluidResourceType type, RegistryEntry<T> entry) {
			fluids.put(type, entry);
			registryFluids.add(entry);
			return entry;
		}
		
		protected <T extends Item> RegistryEntry<T> add(ItemResourceType type, RegistryEntry<T> entry) {
			items.put(type, entry);
			registryItems.add(entry);
			return entry;
		}
		
		protected <T extends Block> RegistryEntry<T> addExisting(BlockResourceType type, RegistryEntry<T> entry) {
			blocks.put(type, entry);
			return entry;
		}
		
		protected <T extends Fluid> RegistryEntry<T> addExisting(FluidResourceType type, RegistryEntry<T> entry) {
			fluids.put(type, entry);
			return entry;
		}
		
		protected <T extends Item> RegistryEntry<T> addExisting(ItemResourceType type, RegistryEntry<T> entry) {
			items.put(type, entry);
			return entry;
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
