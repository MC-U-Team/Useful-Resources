package info.u_team.useful_resources.api.list;

import java.util.*;

import info.u_team.u_team_core.util.CastUtil;

public class TypeList<E, S> {
	
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
	
	public S add(E element) {
		list.add(element);
		return CastUtil.uncheckedCast(this);
	}
	
	@SafeVarargs
	public final S add(E... elements) {
		for (final E element : elements) {
			add(element);
		}
		return CastUtil.uncheckedCast(this);
	}
	
}
