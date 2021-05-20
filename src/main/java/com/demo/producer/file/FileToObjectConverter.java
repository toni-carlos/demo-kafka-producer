package com.demo.producer.file;

import com.demo.producer.model.Event;

import java.io.BufferedReader;
import java.util.List;

public interface FileToObjectConverter {
    List<Event> converter(String path);
    List<Event> filePojo(BufferedReader reader);
}