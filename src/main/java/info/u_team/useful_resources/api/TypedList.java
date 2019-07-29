package info.u_team.useful_resources.api;

import java.util.List;

public class TypedList<E> {
	
	private final ListType type;
	private final List<E> list;
	
	public TypedList(ListType type, List<E> list) {
		this.type = type;
		this.list = list;
	}
	
	public ListType getType() {
		return type;
	}
	
	public List<E> getList() {
		return list;
	}
}
