package info.u_team.useful_resources.data.provider.block_state;

import info.u_team.useful_resources.api.resource.datagen.ResourceDataCategory;
import info.u_team.useful_resources.data.provider.UsefulResourcesBlockStateProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.RegisterSubDataProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.SubDataProvider;

@RegisterSubDataProvider(UsefulResourcesBlockStateProvider.class)
public class OreSubDataProvider implements SubDataProvider<UsefulResourcesBlockStateProvider> {
	
	@Override
	public void register(UsefulResourcesBlockStateProvider provider) {
		for (final ResourceDataCategory category : ResourceDataCategory.CATEGORIES) {
			provider.models().withExistingParent("base/block/type/" + category.getName() + "_stone_ore", BaseColoredSubDataProvider.COLORED_OVERLAY_BLOCK) //
					.texture("uncolored", "block/" + category.getName() + "_stone_ore") //
					.texture("colored", "block/" + category.getName() + "_ore");
			
			provider.models().withExistingParent("base/block/type/" + category.getName() + "_deepslate_ore", BaseColoredSubDataProvider.COLORED_OVERLAY_BLOCK) //
					.texture("uncolored", "block/" + category.getName() + "_deepslate_ore") //
					.texture("colored", "block/" + category.getName() + "_ore");
			
			provider.models().withExistingParent("base/block/type/" + category.getName() + "_netherrack_ore", BaseColoredSubDataProvider.COLORED_OVERLAY_BLOCK) //
					.texture("uncolored", "block/" + category.getName() + "_netherrack_ore") //
					.texture("colored", "block/" + category.getName() + "_ore");
			
			provider.models().withExistingParent("base/block/type/" + category.getName() + "_endstone_ore", BaseColoredSubDataProvider.COLORED_OVERLAY_BLOCK) //
					.texture("uncolored", "block/" + category.getName() + "_endstone_ore") //
					.texture("colored", "block/" + category.getName() + "_ore");
		}
	}
	
}
