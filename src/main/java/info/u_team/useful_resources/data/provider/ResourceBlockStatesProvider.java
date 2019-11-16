package info.u_team.useful_resources.data.provider;

import java.util.stream.Stream;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.type.Resources;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		Resources.getValues().forEach(resource -> {
			Stream.of(resource.getBlocks().getArray()).forEach(block -> {
				final String blockName = block.getRegistryName().getPath();
				simpleBlock(block, cubeAll(blockName, modLoc("block/" + resource.getName() + "/" + blockName.replace(resource.getName() + "_", ""))));
			});
		});
	}
}
