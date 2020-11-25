package info.u_team.useful_resources.api.resource.special;

import static info.u_team.useful_resources.api.type.BlockResourceType.*;
import static info.u_team.useful_resources.util.GenerationUtil.*;
import static info.u_team.useful_resources.util.MaterialUtil.*;

import net.minecraft.item.Rarity;

public class CommonGemResource extends BasicGemResource {
	
	public CommonGemResource(String name, int color) {
		super(name, color, Rarity.COMMON, 2, 3, 3, 7);
		setArmor(createArmor(25, new int[] { 3, 6, 8, 3 }, 11));
		setHorseArmor(11);
		setTools(createTools(new float[] { 5.0F, 0, 1, 1.5F, 3 }, new float[] { -3.0F, 0, -2.8F, -3, -2.4F }, 3, 1000, 8, 3, 10));
		setProperty("oreSmeltingXp", 1);
		setGenerationDefault(ORE, state -> createOreFeatureRangeOverworld(state, 7, 3, 0, 0, 48));
		setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeNether(state, 6, 2, 0, 0, 128));
		setGenerationDefault(NETHER_ORE, state -> createOreFeatureRangeEnd(state, 4, 4, 0, 0, 64));
	}
	
}
