package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.*;

public class UsefulResourcesBlockModelsProvider extends CommonBlockStatesProvider {
	
	public UsefulResourcesBlockModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerStatesAndModels() {
		withExistingParent("colored_cube_all", "block") //
				.texture("particle", "#all") //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#all") //
							.cullface(direction) //
							.tintindex(0);
				}) //
				.end();
		
		withExistingParent("colored_overlay_cube_all", modLoc("colored_cube_all")) //
				.texture("all", "#colored") //
				.element() //
				.from(0, 0, 0) //
				.to(16, 16, 16) //
				.allFaces((direction, builder) -> {
					builder.texture("#uncolored") //
							.cullface(direction);
				}) //
				.end();
	}
	
}
