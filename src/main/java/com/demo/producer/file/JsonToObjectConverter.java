package com.demo.producer.file;

import com.demo.producer.model.Event;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class JsonToObjectConverter implements FileToObjectConverter {

    public List<Event> converter(String path) {

        List<Event> values = null;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(path)),
                        StandardCharsets.UTF_8))) {

            values = filePojo(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return values;
    }

    public List<Event> filePojo(BufferedReader reader) {
        Gson gson = new Gson();
        List<Event> events = Arrays.asList(gson.fromJson(reader, Event[].class));
        return events;

    }
}
