package com.demo.producer;

import com.demo.producer.streamers.KafkaStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
class Application implements CommandLineRunner {

    @Autowired
    private KafkaStreamer kafkaStreamer;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application Started !!");
        kafkaStreamer.start();
        System.out.println("Application Finish !!");
    }
}

