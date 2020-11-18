package info.u_team.useful_resources.data.provider;
/* TODO
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.data.provider.common.CommonWorldGenFeatureProvider;

public class ResourceWorldGenFeatureProvider extends CommonWorldGenFeatureProvider {
	
	public ResourceWorldGenFeatureProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void addFeatures() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getDataGeneratorConfigurator().getWorldGeneration().forEach((name, worldGenFeature) -> {
				addFeature(resource.getName() + "/" + name, worldGenFeature.get());
			});
		});
	}
	
} */
