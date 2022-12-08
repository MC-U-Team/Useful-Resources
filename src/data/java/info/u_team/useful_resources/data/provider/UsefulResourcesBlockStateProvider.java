package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockStateProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.api.resource.datagen.ResourceDataCategory;
import info.u_team.useful_resources.data.util.GenerationResourceRegistry;
import info.u_team.useful_resources.data.util.SubDataProviderUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;

public class UsefulResourcesBlockStateProvider extends CommonBlockStateProvider {
	
	public UsefulResourcesBlockStateProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		SubDataProviderUtil.registerSubProvider(this);
		
		GenerationResourceRegistry.forEachBlock((resource, type, block) -> {
			final ResourceDataCategory category = resource.getDataGenInfo().get().getCategory();
			
			final ResourceLocation baseModel;
			if (type.getName().equals("ore")) {
				baseModel = modLoc("base/block/type/" + category.getName() + "_stone_ore");
			} else if (type.getName().equals("deepslate_ore")) {
				baseModel = modLoc("base/block/type/" + category.getName() + "_deepslate_ore");
			} else if (type.getName().equals("netherrack_ore")) {
				baseModel = modLoc("base/block/type/" + category.getName() + "_netherrack_ore");
			} else if (type.getName().equals("endstone_ore")) {
				baseModel = modLoc("base/block/type/" + category.getName() + "_endstone_ore");
			} else {
				baseModel = null;
			}
			
			final ModelFile file = models().withExistingParent(getPath(block), baseModel);
			
			simpleBlock(block, file);
			
			simpleBlockItem(block, file);
		});
	}
	
}
