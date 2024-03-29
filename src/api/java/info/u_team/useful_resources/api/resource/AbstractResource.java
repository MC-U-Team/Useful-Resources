package info.u_team.useful_resources.api.resource;

import java.util.function.Supplier;

import info.u_team.useful_resources.api.resource.datagen.AbstractResourceDataGenInfo;
import net.minecraft.world.item.Rarity;

public interface AbstractResource {
	
	String getName();
	
	int getColor();
	
	Rarity getRarity();
	
	AbstractResourceEntries getEntries();
	
	Supplier<AbstractResourceDataGenInfo> getDataGenInfo();
	
}
