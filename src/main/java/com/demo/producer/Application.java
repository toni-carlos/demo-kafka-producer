package com.demo.producer;

import static java.nio.file.Files.newInputStream;
import static java.util.Optional.empty;
import static java.util.Optional.of;

import com.demo.producer.config.ApplicationProperties;
import com.demo.producer.streamers.KafkaStreamer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws IOException {
		var properties = loadProperties();
		if (properties.isPresent()) {
			new KafkaStreamer(properties.get()).start();
		}
	}

	private static Optional<ApplicationProperties> loadProperties() {
		final var path = "config/application.yaml";
		if (null == path) {
			System.err.println("Usage: -Dconfig=<file.yml>");
			return empty();
		}

		try (final var inputStream = newInputStream(Paths.get(path))) {
			return of(new Yaml().loadAs(inputStream, ApplicationProperties.class));
		} catch (IOException e) {
			LOGGER.error("error loading properties in {}", path, e);
			return empty();
		}
	}
}

