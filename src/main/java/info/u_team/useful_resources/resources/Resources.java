package info.u_team.useful_resources.resources;

import static info.u_team.useful_resources.util.MaterialUtil.*;

import info.u_team.useful_resources.api.ResourceRegistry;
import info.u_team.useful_resources.api.resource.IResource;
import info.u_team.useful_resources.api.resource.special.BasicIngotResource;
import net.minecraft.item.Rarity;

public class Resources {
	
	public static final IResource COPPER = new BasicIngotResource("copper", 0xcf6617, Rarity.COMMON, 1, 3); //
			//.setArmor(createArmor(12, new int[] { 2, 5, 6, 2 }, 9)) //
			//.setHorseArmor(6) //
			//.setTools(createTools(new float[] { 6, 0, 1, 1.5F, 3 }, new float[] { -3.1F, -1, -2.8F, -3, -2.4F }, 2, 200, 5, 1, 14));
	
	public static void register() {
		ResourceRegistry.register(COPPER);
	}
	
}
