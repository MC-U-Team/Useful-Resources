package info.u_team.useful_resources.api.resource;

import net.minecraft.world.item.Rarity;

public interface AbstractResource {
	
	String getName();
	
	int getColor();
	
	Rarity getRarity();
	
	AbstractResourceEntries getEntries();
	
}
