package info.u_team.useful_resources.api;

import java.util.ArrayList;

public class TypedArrayList<E> extends ArrayList<E> {
	
	private static final long serialVersionUID = 1L;
	
	private final ListType type;
	
	public TypedArrayList(ListType type) {
		this.type = type;
	}
	
	public ListType getType() {
		return type;
	}
}
