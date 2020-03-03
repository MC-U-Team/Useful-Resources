package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.data.*;
import info.u_team.useful_resources.api.type.BlockResourceType;
import net.minecraft.util.ResourceLocation;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		generateBaseModels();
		
		ResourceRegistry.getResources().forEach(resource -> {
			resource.getBlocks().forEach((type, block) -> {
				simpleBlock(block, models().withExistingParent(getPath(block), getBaseModel(type, resource.getDataGeneratorConfigurator())));
			});
		});
	}
	
	private ResourceLocation getBaseModel(BlockResourceType type, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		final String baseModel;
		final OreType oreType = dataGeneratorConfigurator.getOreType();
		if (type == BlockResourceType.ORE) {
			baseModel = oreType.getName() + "_stone_ore";
		} else if (type == BlockResourceType.NETHER_ORE) {
			baseModel = oreType.getName() + "_netherrack_ore";
		} else if (type == BlockResourceType.BLOCK) {
			baseModel = "block";
		} else {
			baseModel = "invalid";
		}
		return modLoc("base/block/" + baseModel);
	}
	
	private void generateBaseModels() {
		models().withExistingParent("base/block/colored_block", "block") //
				.texture("particle", "#colored") //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#colored") //
							.cullface(direction) //
							.tintindex(1);
				}) //
				.end();
		
		models().withExistingParent("base/block/colored_overlay_block", "block") //
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
							.tintindex(1);
				}) //
				.end();
		
		models().withExistingParent("base/block/block", modLoc("base/block/colored_block")) //
				.texture("colored", "block/block");
		
		models().withExistingParent("base/block/ingot_stone_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/stone_ingot_ore_background") //
				.texture("colored", "block/ingot_ore");
		
		models().withExistingParent("base/block/ingot_netherrack_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/netherrack_ingot_ore_background") //
				.texture("colored", "block/ingot_ore");
	}
}
