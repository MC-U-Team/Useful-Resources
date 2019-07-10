package info.u_team.useful_resources.api;

import info.u_team.u_team_core.api.registry.IUArrayRegistryType;
import net.minecraft.item.Item;

public interface IResourceItems extends IUArrayRegistryType<Item> {
	
	Item getIngot();
	
	Item getNugget();
	
	Item getPlate();
	
	Item getDensePlate();
	
	Item getGear();
	
	@Override
	default Item[] getArray() {
		return new Item[] { getIngot(), getNugget(), getPlate(), getDensePlate(), getGear() };
	}
	
}
