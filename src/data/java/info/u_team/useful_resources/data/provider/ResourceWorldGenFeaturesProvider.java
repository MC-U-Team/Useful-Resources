package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.data.provider.common.CommonWorldGenFeaturesProvider;

public class ResourceWorldGenFeaturesProvider extends CommonWorldGenFeaturesProvider {
	
	public ResourceWorldGenFeaturesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void addFeatures() {
		ResourceRegistry.forEach(resource -> {
			resource.getDataGeneratorConfigurator().getWorldGeneration().forEach((name, worldGenFeature) -> {
				addFeature(resource.getName() + "/" + name, worldGenFeature.get());
			});
		});
	}
}
