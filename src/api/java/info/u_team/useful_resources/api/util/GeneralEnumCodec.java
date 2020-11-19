package info.u_team.useful_resources.api.util;

import java.util.Optional;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.*;

public class GeneralEnumCodec {
	
	public static <E extends Enum<E>> Codec<E> createCodec(Class<E> enumClass) {
		return new Codec<E>() {
			
			@Override
			public <T> DataResult<T> encode(E input, DynamicOps<T> ops, T prefix) {
				if (ops.compressMaps()) {
					return ops.mergeToPrimitive(prefix, ops.createInt(input.ordinal()));
				} else {
					return ops.mergeToPrimitive(prefix, ops.createString(input.name()));
				}
			}
			
			@Override
			public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> ops, T input) {
				if (ops.compressMaps()) {
					return ops.getNumberValue(input).flatMap(id -> {
						return Optional.ofNullable(enumClass.getEnumConstants()[id.intValue()]).map(DataResult::success).orElseGet(() -> {
							return DataResult.error("Unknown enum element id: " + id);
						});
					}).map(serializable -> {
						return Pair.of(serializable, ops.empty());
					});
				} else {
					return ops.getStringValue(input).flatMap(id -> {
						return Optional.ofNullable(Enum.valueOf(enumClass, id)).map(DataResult::success).orElseGet(() -> {
							return DataResult.error("Unknown enum element name: " + id);
						});
					}).map(serializable -> {
						return Pair.of(serializable, ops.empty());
					});
				}
			}
			
			public String toString() {
				return "StringRepresentable[" + enumClass + "]";
			}
		};
	}
}
