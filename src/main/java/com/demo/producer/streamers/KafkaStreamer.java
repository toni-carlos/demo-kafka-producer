package com.demo.producer.streamers;

import com.demo.producer.file.JsonToObjectConverter;
import com.demo.producer.kafka.ProducerMessage;
import com.demo.producer.transformer.SchemaEnrich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class KafkaStreamer {

    @Value("${jsonDataPath}")
    private String jsonDataPath;

    @Autowired
    private JsonToObjectConverter jsonToObjectConverter;

    @Autowired
    private SchemaEnrich schemaEnrich;

    @Autowired
    private ProducerMessage producerMessage;

    public void start() {

        jsonToObjectConverter
                .converter(jsonDataPath).stream()
                .map(schemaEnrich::enrich)
                .forEachOrdered(producerMessage::send);
    }
}
