package info.u_team.useful_resources.resource.feature;

import info.u_team.useful_resources.api.resource.AbstractRegisterProvider;
import info.u_team.useful_resources.block.BaseItem;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceFeatureCreator;
import info.u_team.useful_resources.resource.AbstractResourceBuilder.ResourceProperties;
import info.u_team.useful_resources.resource.ResourceFeature;
import info.u_team.useful_resources.resource.common.CommonResourceTypes;

public class RawOreResourceFeature extends ResourceFeature {
	
	public static ResourceFeatureCreator create() {
		return (properties, registerProvider) -> new RawOreResourceFeature(properties, registerProvider);
	}
	
	protected RawOreResourceFeature(ResourceProperties resourceProperties, AbstractRegisterProvider registerProvider) {
		super(resourceProperties, registerProvider);
		registerItem(CommonResourceTypes.RAW, () -> new BaseItem(resourceProperties.rarity()));
	}
	
}
