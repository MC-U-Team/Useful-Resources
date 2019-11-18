package info.u_team.useful_resources.api;

@FunctionalInterface
public interface IResourceFeatureBuilder {
	
	IResourceFeature build(String name);
	
}
