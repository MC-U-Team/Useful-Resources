package info.u_team.useful_resources.util;

import java.io.*;
import java.nio.file.*;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class ConfigUtil {
	
	public static void loadConfig(Path directory, String name, Gson gson, Consumer<JsonWriter> write, Consumer<BufferedReader> read) throws IOException {
		final Path path = directory.resolve(name + ".json");
		if (Files.exists(path) && Files.isReadable(path) && Files.isReadable(path)) {
			try (BufferedReader reader = Files.newBufferedReader(path)) {
				read.accept(reader);
			}
		} else {
			Files.deleteIfExists(path);
			Files.createDirectories(directory);
			Files.createFile(path);
			try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path); JsonWriter writer = GsonUtil.createTagWriter(gson, bufferedWriter)) {
				write.accept(writer);
			}
		}
	}
	
}
