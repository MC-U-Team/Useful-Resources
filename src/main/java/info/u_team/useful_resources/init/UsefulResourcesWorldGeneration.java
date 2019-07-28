package info.u_team.useful_resources.init;

public class UsefulResourcesWorldGeneration {
	
	public static void setup() {
		// final CommonConfig config = CommonConfig.getInstance();
		// if (!config.worldGenerationEnabled.get()) {
		// return;
		// }
		// ForgeRegistries.BIOMES.getValues().forEach(biome -> Resources.VALUES.forEach(resource -> addResource(biome,
		// resource)));
	}
	
//	private static void addResource(Biome biome, IResource resource) {
//		final IResourceBlocks blocks = resource.getBlocks();
//		final Supplier<IResourceConfig> config = resource.getConfig();
//		addGeneratable(biome, blocks.getBlock(ResourceBlockTypes.ORE).getDefaultState(), FillerBlockType.NATURAL_STONE, config.get().getOreGeneratable().get());
//		addGeneratable(biome, blocks.getBlock(ResourceBlockTypes.NETHER_ORE).getDefaultState(), FillerBlockType.NETHERRACK, config.get().getNetherOreGeneratable().get());
//	}
//	
//	private static void addGeneratable(Biome biome, BlockState state, FillerBlockType fillerType, IGeneratable generatable) {
//		if (!generatable.isEnabled()) {
//			return;
//		}
//		final ListType biomeCategoryListType = generatable.getBiomeCategoryListType();
//		final List<Category> biomeCategories = generatable.getBiomeCategories();
//		if (biomeCategoryListType == ListType.BLACKLIST && biomeCategories.contains(biome.getCategory()) || biomeCategoryListType == ListType.WHITELIST && !biomeCategories.contains(biome.getCategory())) {
//			return;
//		}
//		
//		final ListType biomeListType = generatable.getBiomeListType();
//		final List<Biome> biomes = generatable.getBiomes();
//		if (biomeListType == ListType.BLACKLIST && biomes.contains(biome) || biomeListType == ListType.WHITELIST && !biomes.contains(biome)) {
//			return;
//		}
//		
//		if (generatable.getGenerationConfig() == GenerationConfig.COUNT_RANGE) {
//			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(fillerType, state, generatable.getVeinSize()), Placement.COUNT_RANGE, generatable.getCountRangeConfig()));
//		} else if (generatable.getGenerationConfig() == GenerationConfig.COUNT_DEPTH_AVERAGE) {
//			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(fillerType, state, generatable.getVeinSize()), Placement.COUNT_DEPTH_AVERAGE, generatable.getDepthAverageConfig()));
//		}
//	}
	
}
