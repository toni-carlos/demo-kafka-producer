package com.demo.file;

import com.demo.model.Event;

import java.io.BufferedReader;
import java.util.List;

public interface FileToObjectConverter {
    List<Event> converter(String path);
    List<Event> filePojo(BufferedReader reader);
}