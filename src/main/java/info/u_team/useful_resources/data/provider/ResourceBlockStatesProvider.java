package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.type.BlockResourceType;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		// Make base blocks
		models().withExistingParent("base/colored_block", "block") //
				.texture("particle", "#colored") //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#colored") //
							.cullface(direction) //
							.tintindex(0);
				}) //
				.end();
		
		models().withExistingParent("base/colored_overlay_block", "block") //
				.texture("particle", "#colored") //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#uncolored") //
							.cullface(direction);
				}) //
				.end() //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#colored") //
							.cullface(direction) //
							.tintindex(0);
				}) //
				.end();
		
		models().withExistingParent("base/default_block", modLoc("base/colored_block")) //
				.texture("colored", "block/default_block");
		
		models().withExistingParent("base/default_stone_ore", modLoc("base/colored_overlay_block")) //
				.texture("uncolored", "block/stone_background") //
				.texture("colored", "block/default_ore");
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().forEach((type, block) -> {
				final String blockName = block.getRegistryName().getPath();
				// simpleBlock(block, cubeAll(blockName, modLoc("block/" + resource.getName() + "/" +
				// blockName.replace(resource.getName() + "_", ""))));
				if (type == BlockResourceType.BLOCK) {
					simpleBlock(block, models().withExistingParent(blockName, modLoc("base/default_block")));
				} else {
					simpleBlock(block, models().withExistingParent(blockName, modLoc("base/default_stone_ore")));
				}
			});
		});
	}
}
