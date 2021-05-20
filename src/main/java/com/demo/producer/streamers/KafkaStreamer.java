package com.demo.producer.streamers;

import com.demo.producer.config.ApplicationProperties;
import com.demo.producer.file.JsonToObjectConverter;
import com.demo.producer.kafka.ProducerMessage;
import com.demo.producer.transformer.SchemaEnrich;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Properties;


public class KafkaStreamer {

    private final ApplicationProperties applicationProperties;

    public KafkaStreamer(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void start() {

        var enricher = new SchemaEnrich();
        var fileToObjectConverter = new JsonToObjectConverter();
        var producerMessage = new ProducerMessage(
                new KafkaProducerProperties(
                        this.applicationProperties.getBootstrapServers(),
                        this.applicationProperties.getSchemaRegistryUrl()),
                this.applicationProperties.getOutpuTopic());

        fileToObjectConverter
                .converter(this.applicationProperties.getJsonDataPath()).stream()
                .map(enricher::enrich)
                .forEachOrdered(producerMessage::send);
    }

    private static final class KafkaProducerProperties extends Properties {

        KafkaProducerProperties(String bootstrapServers, String schemaRegistryUrl) {
            super();

            this.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            this.put(ProducerConfig.ACKS_CONFIG, "all");
            this.put(ProducerConfig.LINGER_MS_CONFIG, 100);
            this.put(ProducerConfig.RETRIES_CONFIG, 3);
            this.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            this.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            this.put(ProducerConfig.BATCH_SIZE_CONFIG, 100);
            this.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
            this.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
            this.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, false);
        }
    }
}
