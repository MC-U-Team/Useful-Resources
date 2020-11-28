package info.u_team.useful_resources.data.provider;

import java.util.Map;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.generators.*;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		generateBaseModels();
		
		ResourceRegistry.forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				final IDataGeneratorConfigurator dataGeneratorConfigurator = resource.getDataGeneratorConfigurator();
				if (type == BlockResourceType.BARS) {
					barsBlock(block, type, dataGeneratorConfigurator);
				} else {
					simpleBlock(block, models().withExistingParent(getPath(block), getBaseModel(type, dataGeneratorConfigurator)));
				}
			});
		});
	}
	
	private ResourceLocation getBaseModel(BlockResourceType type, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		final Map<String, Object> extraProperties = dataGeneratorConfigurator.getExtraProperties();
		
		final String baseModel;
		
		if (extraProperties.containsKey(type.getName() + "ModelOverride")) {
			baseModel = ObjectUtil.getString(extraProperties.get(type.getName() + "ModelOverride"));
		} else {
			final ResourceType resourceType;
			if (ObjectUtil.getBoolean(extraProperties.getOrDefault("ingotModel", false))) {
				resourceType = ResourceType.INGOT;
			} else {
				resourceType = dataGeneratorConfigurator.getResourceType();
			}
			
			if (type == BlockResourceType.ORE) {
				baseModel = resourceType.getName() + "_stone_ore";
			} else if (type == BlockResourceType.NETHER_ORE) {
				baseModel = resourceType.getName() + "_netherrack_nether_ore";
			} else if (type == BlockResourceType.END_ORE) {
				baseModel = resourceType.getName() + "_endstone_end_ore";
			} else if (type == BlockResourceType.BLOCK) {
				baseModel = resourceType.getName() + "_" + type.getName();
			} else {
				baseModel = type.getName();
			}
		}
		
		return modLoc("base/block/special/" + baseModel);
	}
	
	private void generateBaseModels() {
		// Basic types
		
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
		
		// Special models for each type
		
		// BlockResourceType.ORE
		
		// INGOT
		models().withExistingParent("base/block/special/ingot_stone_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/stone_ingot_ore_background") //
				.texture("colored", "block/ingot_ore");
		
		// GEM
		models().withExistingParent("base/block/special/gem_stone_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/stone_gem_ore_background") //
				.texture("colored", "block/gem_ore");
		
		// BlockResourceType.NETHER_ORE
		
		// INGOT
		models().withExistingParent("base/block/special/ingot_netherrack_nether_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/netherrack_ingot_ore_background") //
				.texture("colored", "block/ingot_ore");
		
		// GEM
		models().withExistingParent("base/block/special/gem_netherrack_nether_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/netherrack_gem_ore_background") //
				.texture("colored", "block/gem_ore");
		
		// LAPIS (special)
		models().withExistingParent("base/block/special/lapis_netherrack_nether_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/netherrack_lapis_ore_background") //
				.texture("colored", "block/lapis_ore");
		
		// BlockResourceType.END_ORE
		
		// INGOT
		models().withExistingParent("base/block/special/ingot_endstone_end_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/endstone_ingot_ore_background") //
				.texture("colored", "block/ingot_ore");
		
		// GEM
		models().withExistingParent("base/block/special/gem_endstone_end_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/endstone_gem_ore_background") //
				.texture("colored", "block/gem_ore");
		
		// LAPIS (special)
		models().withExistingParent("base/block/special/lapis_endstone_end_ore", modLoc("base/block/colored_overlay_block")) //
				.texture("uncolored", "block/endstone_lapis_ore_background") //
				.texture("colored", "block/lapis_ore");
		
		// BlockResourceType.BLOCK
		
		// INGOT
		models().withExistingParent("base/block/special/ingot_block", modLoc("base/block/colored_block")) //
				.texture("colored", "block/ingot_block");
		
		// GEM
		models().withExistingParent("base/block/special/gem_block", modLoc("base/block/colored_block")) //
				.texture("colored", "block/gem_block");
		
		// BlockResourceType.BARS
		
		models().getBuilder("base/block/special/bars_cap") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("bars", "block/bars") //
				.element() //
				.from(8, 0, 8) //
				.to(8, 16, 9) //
				.face(Direction.WEST) //
				.uvs(8, 0, 7, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.EAST) //
				.uvs(7, 0, 8, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 9) //
				.to(9, 16, 9) //
				.face(Direction.NORTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end();
		
		models().getBuilder("base/block/special/bars_cap_alt") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("bars", "block/bars") //
				.element() //
				.from(8, 0, 7) //
				.to(8, 16, 8) //
				.face(Direction.WEST) //
				.uvs(8, 0, 9, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.EAST) //
				.uvs(9, 0, 8, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 7) //
				.to(9, 16, 7) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end();
		
		models().getBuilder("base/block/special/bars_post") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("bars", "block/bars") //
				.element() //
				.from(8, 0, 7) //
				.to(8, 16, 9) //
				.face(Direction.WEST) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.EAST) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 8) //
				.to(9, 16, 8) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end();
		
		models().getBuilder("base/block/special/bars_post_ends") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("edge", "block/bars") //
				.element() //
				.from(7, 0.001F, 7) //
				.to(9, 0.001F, 9) //
				.face(Direction.DOWN) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 7) //
				.to(9, 15.999F, 9) //
				.face(Direction.DOWN) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end();
		
		models().getBuilder("base/block/special/bars_side") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("bars", "block/bars") //
				.texture("edge", "block/bars") //
				.element() //
				.from(8, 0, 0) //
				.to(8, 16, 8) //
				.face(Direction.WEST) //
				.uvs(16, 0, 8, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.EAST) //
				.uvs(8, 0, 16, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 0) //
				.to(9, 16, 7) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.cullface(Direction.NORTH) //
				.end() //
				.end() //
				.element() //
				.from(7, 0.001F, 0) //
				.to(9, 0.001F, 7) //
				.face(Direction.DOWN) //
				.uvs(9, 0, 7, 7) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 0, 9, 7) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 0) //
				.to(9, 15.999F, 7) //
				.face(Direction.DOWN) //
				.uvs(9, 0, 7, 7) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 0, 9, 7) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end();
		
		models().getBuilder("base/block/special/bars_side_alt") //
				.ao(false) //
				.texture("particle", "block/bars") //
				.texture("bars", "block/bars") //
				.texture("edge", "block/bars") //
				.element() //
				.from(8, 0, 8) //
				.to(8, 16, 16) //
				.face(Direction.WEST) //
				.uvs(8, 0, 0, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.face(Direction.EAST) //
				.uvs(0, 0, 8, 16) //
				.texture("#bars") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 9) //
				.to(9, 16, 16) //
				.face(Direction.SOUTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.cullface(Direction.SOUTH) //
				.end() //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 0.001F, 9) //
				.to(9, 0.001F, 16) //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 9) //
				.to(9, 15.999F, 16) //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.tintindex(1) //
				.end() //
				.end();
		
		// BlockResourceType.MOLTEN_FLUID
		models().getBuilder("base/block/special/molten_fluid") //
				.texture("particle", mcLoc("block/water_still"));
	}
	
	public void barsBlock(Block block, BlockResourceType type, IDataGeneratorConfigurator dataGeneratorConfigurator) {
		
		final ModelFile postEnds = models().withExistingParent(getPath(block) + "_post_ends", getBaseModel(type, dataGeneratorConfigurator) + "_post_ends");
		final ModelFile post = models().withExistingParent(getPath(block) + "_post", getBaseModel(type, dataGeneratorConfigurator) + "_post");
		final ModelFile side = models().withExistingParent(getPath(block) + "_side", getBaseModel(type, dataGeneratorConfigurator) + "_side");
		final ModelFile sideAlt = models().withExistingParent(getPath(block) + "_side_alt", getBaseModel(type, dataGeneratorConfigurator) + "_side_alt");
		final ModelFile cap = models().withExistingParent(getPath(block) + "_cap", getBaseModel(type, dataGeneratorConfigurator) + "_cap");
		final ModelFile capAlt = models().withExistingParent(getPath(block) + "_cap_alt", getBaseModel(type, dataGeneratorConfigurator) + "_cap_alt");
		
		final MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
		builder.part().modelFile(postEnds).addModel().end();
		builder.part().modelFile(post).addModel().condition(FourWayBlock.WEST, false).condition(FourWayBlock.EAST, false).condition(FourWayBlock.SOUTH, false).condition(FourWayBlock.NORTH, false).end();
		builder.part().modelFile(cap).addModel().condition(FourWayBlock.WEST, false).condition(FourWayBlock.EAST, false).condition(FourWayBlock.SOUTH, false).condition(FourWayBlock.NORTH, true).end();
		builder.part().modelFile(cap).rotationY(90).addModel().condition(FourWayBlock.WEST, false).condition(FourWayBlock.EAST, true).condition(FourWayBlock.SOUTH, false).condition(FourWayBlock.NORTH, false).end();
		builder.part().modelFile(capAlt).addModel().condition(FourWayBlock.WEST, false).condition(FourWayBlock.EAST, false).condition(FourWayBlock.SOUTH, true).condition(FourWayBlock.NORTH, false).end();
		builder.part().modelFile(capAlt).rotationY(90).addModel().condition(FourWayBlock.WEST, true).condition(FourWayBlock.EAST, false).condition(FourWayBlock.SOUTH, false).condition(FourWayBlock.NORTH, false).end();
		builder.part().modelFile(side).addModel().condition(FourWayBlock.NORTH, true).end();
		builder.part().modelFile(side).rotationY(90).addModel().condition(FourWayBlock.EAST, true).end();
		builder.part().modelFile(sideAlt).addModel().condition(FourWayBlock.SOUTH, true).end();
		builder.part().modelFile(sideAlt).rotationY(90).addModel().condition(FourWayBlock.WEST, true).end();
	}
}
