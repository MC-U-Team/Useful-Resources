package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().values().forEach(block -> {
				final String blockName = block.getRegistryName().getPath();
				simpleBlock(block, cubeAll(blockName, modLoc("block/" + resource.getName() + "/" + blockName.replace(resource.getName() + "_", ""))));
			});
		});
	}
}
