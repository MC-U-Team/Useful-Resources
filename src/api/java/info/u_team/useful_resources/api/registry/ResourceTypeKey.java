package info.u_team.useful_resources.api.registry;

import java.util.Objects;

import info.u_team.useful_resources.api.resource.AbstractResourceType;

public final class ResourceTypeKey<T> {
	
	public static <T> ResourceTypeKey<T> create(AbstractResourceType<T> type) {
		return new ResourceTypeKey<>(type);
	}
	
	private final AbstractResourceType<T> type;
	
	private ResourceTypeKey(AbstractResourceType<T> type) {
		this.type = Objects.requireNonNull(type);
	}
	
	public AbstractResourceType<T> getType() {
		return type;
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
