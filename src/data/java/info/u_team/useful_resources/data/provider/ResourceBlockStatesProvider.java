package info.u_team.useful_resources.data.provider;

import java.util.Map;

import info.u_team.u_team_core.data.*;
import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator;
import info.u_team.useful_resources.api.resource.data.IDataGeneratorConfigurator.ResourceType;
import info.u_team.useful_resources.api.type.BlockResourceType;
import info.u_team.useful_resources.util.ObjectUtil;
import net.minecraft.util.ResourceLocation;

public class ResourceBlockStatesProvider extends CommonBlockStatesProvider {
	
	public ResourceBlockStatesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		generateBaseModels();
		
		ResourceRegistry.getResources().forEach(resource -> {
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
		
		// BlockResourceType.MOLTEN_FLUID
		models().getBuilder("base/block/special/molten_fluid") //
				.texture("particle", mcLoc("block/water_still"));
	}
}
