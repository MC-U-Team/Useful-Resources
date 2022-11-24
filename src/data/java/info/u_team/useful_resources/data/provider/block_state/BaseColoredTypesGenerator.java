package info.u_team.useful_resources.data.provider.block_state;

import info.u_team.useful_resources.data.provider.UsefulResourcesBlockStateProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.RegisterSubDataProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.SubDataProvider;

@RegisterSubDataProvider(UsefulResourcesBlockStateProvider.class)
public class BaseColoredTypesGenerator implements SubDataProvider<UsefulResourcesBlockStateProvider> {
	
	@Override
	public void register(UsefulResourcesBlockStateProvider provider) {
		provider.models().withExistingParent("base/block/colored_block", "block") //
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
		
		provider.models().withExistingParent("base/block/colored_overlay_block", "block") //
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
	}
	
}
