package info.u_team.useful_resources.item;

import com.google.gson.annotations.SerializedName;

import info.u_team.useful_resources.api.resource.config.IHorseArmorMaterial;

public class HorseArmorMaterial implements IHorseArmorMaterial {
	
	@SerializedName("armor_points")
	private final int armorPoints;
	
	public HorseArmorMaterial(int armorPoints) {
		this.armorPoints = armorPoints;
	}
	
	@Override
	public int getArmorPoints() {
		return armorPoints;
	}
	
}
