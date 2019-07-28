package info.u_team.useful_resources.api;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
	
	R apply(A a, B b, C c);
}
