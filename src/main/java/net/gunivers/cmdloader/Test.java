package net.gunivers.cmdloader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		try (Stream<String> stream = Files.lines(Paths.get(Test.class.getResource("/Test.json").toURI()))) {
			stream.forEach(System.out::println);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

	}

}
