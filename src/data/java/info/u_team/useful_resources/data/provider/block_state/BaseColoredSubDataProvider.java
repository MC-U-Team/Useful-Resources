package info.u_team.useful_resources.data.provider.block_state;

import info.u_team.useful_resources.UsefulResourcesMod;
import info.u_team.useful_resources.data.provider.UsefulResourcesBlockStateProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.RegisterSubDataProvider;
import info.u_team.useful_resources.data.util.SubDataProviderUtil.SubDataProvider;
import net.minecraft.resources.ResourceLocation;

@RegisterSubDataProvider(UsefulResourcesBlockStateProvider.class)
public class BaseColoredSubDataProvider implements SubDataProvider<UsefulResourcesBlockStateProvider> {
	
	public static final ResourceLocation COLORED_BLOCK = new ResourceLocation(UsefulResourcesMod.MODID, "base/block/colored_block");
	public static final ResourceLocation COLORED_OVERLAY_BLOCK = new ResourceLocation(UsefulResourcesMod.MODID, "base/block/colored_overlay_block");
	
	@Override
	public void register(UsefulResourcesBlockStateProvider provider) {
		provider.models().withExistingParent(COLORED_BLOCK.toString(), "block") //
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
		
		provider.models().withExistingParent(COLORED_OVERLAY_BLOCK.toString(), "block") //
				.renderType("cutout_mipped") //
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
