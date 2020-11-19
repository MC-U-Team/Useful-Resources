package info.u_team.useful_resources.api.list;

import com.mojang.serialization.Codec;

import net.minecraft.util.IStringSerializable;

public enum ListType implements IStringSerializable {
	
	BLACKLIST("blacklist"),
	WHITELIST("whitelist");
	
	public static final Codec<ListType> CODEC = IStringSerializable.createEnumCodec(ListType::values, ListType::byName);
	
	private final String name;
	
	private ListType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String getString() {
		return name;
	}
	
	public static ListType byName(String name) {
		if (WHITELIST.getName().equals(name)) {
			return WHITELIST;
		}
		return BLACKLIST;
	}
}