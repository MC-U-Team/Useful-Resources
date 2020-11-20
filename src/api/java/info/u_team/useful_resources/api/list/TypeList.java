package info.u_team.useful_resources.api.list;

import java.util.*;

public class TypeList<E> {
	
	protected final ListType type;
	protected final List<E> list;
	
	public TypeList(ListType type, List<E> list) {
		this.type = Objects.requireNonNull(type);
		this.list = Objects.requireNonNull(list);
	}
	
	public ListType getType() {
		return type;
	}
	
	public List<E> getList() {
		return list;
	}
	
	/**
	 * Returns true if the element is accepted and not blacklisted or whitelisted
	 * 
	 * @param element
	 * @return true if element is accepted
	 */
	public boolean testWithType(E element) {
		return !(type == ListType.BLACKLIST && list.contains(element) || type == ListType.WHITELIST && !list.contains(element));
	}
	
}
