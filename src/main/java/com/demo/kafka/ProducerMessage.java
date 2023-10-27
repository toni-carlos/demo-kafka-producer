package com.demo.kafka;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
public class ProducerMessage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerMessage.class);
    private KafkaProducer producer;
    private int count = 0;

    @Value("${outputTopic}")
    private String outputTopic;

    private final Properties config;

    public ProducerMessage(Properties config) {
        this.config = config;
        this.producer = new KafkaProducer<String, GenericRecord>(this.config);
    }

    @SuppressWarnings("unchecked")
    public void send(KeyValue<GenericRecord, GenericRecord> record) {
        try {

            LOGGER.info(record.value.toString());

            producer.send(new ProducerRecord(this.outputTopic, record.key, record.value));
            count++;
            LOGGER.info("{} Message sent to topic: {}", count, this.outputTopic);
        } catch (Exception e) {
            LOGGER.error("Something went wrong while running kafka writer ... attempt {}", e);
        }
    }

    public void flush(){
        this.producer.flush();
    }
}
