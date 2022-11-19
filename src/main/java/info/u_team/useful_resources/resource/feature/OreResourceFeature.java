package info.u_team.useful_resources.resource.feature;

import info.u_team.useful_resources.api.registry.ResourceTypeKey;
import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.block.BaseOreBlock;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceFeatureCreator;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceProperties;
import info.u_team.useful_resources.resource.ResourceFeature;
import info.u_team.useful_resources.resource.common.CommonResourceTypes;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class OreResourceFeature extends ResourceFeature {
	
	public static ResourceFeatureCreator createStoneOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, CommonResourceTypes.ORE, MaterialColor.STONE, SoundType.STONE, explosionResistance, explosionResistance);
	}
	
	public static ResourceFeatureCreator createDeepslateOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, CommonResourceTypes.DEEPSLATE_ORE, MaterialColor.DEEPSLATE, SoundType.DEEPSLATE, explosionResistance, explosionResistance);
	}
	
	public static ResourceFeatureCreator createNetherrackOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, CommonResourceTypes.NETHERRACK_ORE, MaterialColor.NETHER, SoundType.NETHER_ORE, explosionResistance, explosionResistance);
	}
	
	public static ResourceFeatureCreator createEndstoneOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, CommonResourceTypes.ENDSTONE_ORE, MaterialColor.NETHER, SoundType.NETHER_ORE, explosionResistance, explosionResistance);
	}
	
	protected OreResourceFeature(ResourceProperties resourceProperties, AbstractRegisterProvider registerProvider, ResourceTypeKey<Block> resourceType, MaterialColor color, SoundType type, float destroyTime, float explosionResistance) {
		super(resourceProperties, registerProvider);
		registerBlock(resourceType, () -> new BaseOreBlock(resourceProperties.rarity(), color, type, destroyTime, explosionResistance, ConstantInt.ZERO));
	}
	
}
