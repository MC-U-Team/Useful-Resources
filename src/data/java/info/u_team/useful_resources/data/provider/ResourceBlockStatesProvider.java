package info.u_team.useful_resources.data.provider;

import java.util.Map;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.util.*;
import net.minecraftforge.client.model.generators.ModelFile;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		generateBaseModels();
		
		ResourceRegistry.forEach(resource -> {
			resource.iterateRegistryBlocks((type, block) -> {
				simpleBlock(block, models().withExistingParent(getPath(block), getBaseModel(type, resource.getDataGeneratorConfigurator())));
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
				.end() //
				.face(Direction.EAST) //
				.uvs(7, 0, 8, 16) //
				.texture("#bars") //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 9) //
				.to(9, 16, 9) //
				.face(Direction.NORTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
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
				.end() //
				.face(Direction.EAST) //
				.uvs(9, 0, 8, 16) //
				.texture("#bars") //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 7) //
				.to(9, 16, 7) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
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
				.end() //
				.face(Direction.EAST) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 8) //
				.to(9, 16, 8) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#bars") //
				.end() //
				.face(Direction.SOUTH) //
				.uvs(9, 0, 7, 16) //
				.texture("#bars") //
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
				.end() //
				.face(Direction.UP) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 7) //
				.to(9, 15.999F, 9) //
				.face(Direction.DOWN) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 7, 9, 9) //
				.texture("#edge") //
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
				.end() //
				.face(Direction.EAST) //
				.uvs(8, 0, 16, 16) //
				.texture("#bars") //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 0) //
				.to(9, 16, 7) //
				.face(Direction.NORTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#edge") //
				.cullface(Direction.NORTH) //
				.end() //
				.end() //
				.element() //
				.from(7, 0.001F, 0) //
				.to(9, 0.001F, 7) //
				.face(Direction.DOWN) //
				.uvs(9, 0, 7, 7) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 0, 9, 7) //
				.texture("#edge") //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 0) //
				.to(9, 15.999F, 7) //
				.face(Direction.DOWN) //
				.uvs(9, 0, 7, 7) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 0, 9, 7) //
				.texture("#edge") //
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
				.end() //
				.face(Direction.EAST) //
				.uvs(0, 0, 8, 16) //
				.texture("#bars") //
				.end() //
				.end() //
				.element() //
				.from(7, 0, 9) //
				.to(9, 16, 16) //
				.face(Direction.SOUTH) //
				.uvs(7, 0, 9, 16) //
				.texture("#edge") //
				.cullface(Direction.SOUTH) //
				.end() //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.end() //
				.end() //
				.element() //
				.from(7, 0.001F, 9) //
				.to(9, 0.001F, 16) //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.end() //
				.end() //
				.element() //
				.from(7, 15.999F, 9) //
				.to(9, 15.999F, 16) //
				.face(Direction.DOWN) //
				.uvs(9, 9, 7, 16) //
				.texture("#edge") //
				.end() //
				.face(Direction.UP) //
				.uvs(7, 9, 9, 16) //
				.texture("#edge") //
				.end() //
				.end();
		
		// BlockResourceType.MOLTEN_FLUID
		models().getBuilder("base/block/special/molten_fluid") //
				.texture("particle", mcLoc("block/water_still"));
	}
}
