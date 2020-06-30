package info.u_team.useful_resources.api.util;

@FunctionalInterface
public interface TriConsumer<A, B, C> {
	
	void accept(A a, B b, C c);
}
