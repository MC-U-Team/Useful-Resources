package info.u_team.useful_resources.resource.feature;

import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.block.BaseOreBlock;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceFeatureCreator;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceProperties;
import info.u_team.useful_resources.resource.ResourceFeature;
import info.u_team.useful_resources.resource.common.CommonResourceTypes;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

public class OreResourceFeature extends ResourceFeature {
	
	public static ResourceFeatureCreator createStoneOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, MaterialColor.STONE, SoundType.STONE, explosionResistance, explosionResistance);
	}
	
	public static ResourceFeatureCreator createDeepslateOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, MaterialColor.DEEPSLATE, SoundType.DEEPSLATE, explosionResistance, explosionResistance);
	}
	
	public static ResourceFeatureCreator createNetherOre(float destroyTime, float explosionResistance) {
		return (properties, registerProvider) -> new OreResourceFeature(properties, registerProvider, MaterialColor.NETHER, SoundType.NETHER_ORE, explosionResistance, explosionResistance);
	}
	
	protected OreResourceFeature(ResourceProperties resourceProperties, AbstractRegisterProvider registerProvider, MaterialColor color, SoundType type, float destroyTime, float explosionResistance) {
		super(resourceProperties, registerProvider);
		registerBlock(CommonResourceTypes.ORE, () -> new BaseOreBlock(resourceProperties.rarity(), color, type, destroyTime, explosionResistance, ConstantInt.ZERO));
	}
	
}
