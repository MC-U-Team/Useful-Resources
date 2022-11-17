package info.u_team.useful_resources.api.registry;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Maps;

import info.u_team.u_team_core.util.CastUtil;
import info.u_team.useful_resources.api.resource.AbstractResourceType;

public final class ResourceTypeKey<T> {
	
	private static final Map<String, ResourceTypeKey<?>> VALUES = Collections.synchronizedMap(Maps.newIdentityHashMap());
	
	public static <T> ResourceTypeKey<T> create(AbstractResourceType<T> type) {
		final String name = type.getName().intern();
		return CastUtil.uncheckedCast(VALUES.computeIfAbsent(name, (unused) -> {
			return new ResourceTypeKey<>(type);
		}));
	}
	
	private final AbstractResourceType<T> type;
	
	private ResourceTypeKey(AbstractResourceType<T> type) {
		this.type = Objects.requireNonNull(type);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(type.getName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceTypeKey<?> other = (ResourceTypeKey<?>) obj;
		return Objects.equals(type.getName(), other.type.getName());
	}
	
}
