package info.u_team.useful_resources.api.type;

public enum ItemResourceType implements IResourceType {
	
	INGOT("ingot"),
	NUGGET("nugget"),
	DUST("dust"),
	PLATE("plate"),
	DENSE_PLATE("dense_plate"),
	GEAR("gear"),
	ROD("rod"),
	
	AXE("axe"),
	HOE("hoe"),
	PICKAXE("pickaxe"),
	SHOVEL("shovel"),
	SWORD("sword"),
	
	HELMET("helmet"),
	CHESTPLATE("chestplate"),
	LEGGINGS("leggings"),
	BOOTS("boots"),
	
	HORSE_ARMOR("horse_armor");
	
	private final String name;
	
	private ItemResourceType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
