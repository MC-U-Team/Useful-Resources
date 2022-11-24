package info.u_team.useful_resources.data.provider;

import info.u_team.u_team_core.data.CommonBlockStateProvider;
import info.u_team.u_team_core.data.GenerationData;
import info.u_team.useful_resources.data.util.SubDataProviderUtil;

public class UsefulResourcesBlockStateProvider extends CommonBlockStateProvider {
	
	public UsefulResourcesBlockStateProvider(GenerationData generationData) {
		super(generationData);
	}
	
	@Override
	public void register() {
		SubDataProviderUtil.registerSubProvider(this);
	}
	
}
