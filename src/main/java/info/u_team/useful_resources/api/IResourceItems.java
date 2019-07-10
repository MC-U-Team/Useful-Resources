package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.item.Item;

public interface IResourceItems extends IUArrayRegistryType<Item> {
	
	Item getIngot();
	
	Item getNugget();
	
	Item getDust();
	
	Item getPlate();
	
	Item getDensePlate();
	
	Item getGear();
	
	Item getRod();
	
	@Override
	default Item[] getArray() {
		return new Item[] { getIngot(), getNugget(), getDust(), getPlate(), getDensePlate(), getGear(), getRod() };
	}
	
}
