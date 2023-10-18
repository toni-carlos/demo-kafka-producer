package com.demo.producer.transformer;

import com.demo.producer.avro.MockarooKey;
import com.demo.producer.avro.MockarooValue;
import com.demo.producer.model.Event;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.KeyValue;
import org.springframework.stereotype.Component;

@Component
public class SchemaEnrich {

    public KeyValue<GenericRecord, GenericRecord> enrich(Event event) {

        var mockarooKey = new MockarooKey();
        var mockarooValue = new MockarooValue();

        mockarooValue.setId(event.getId());
        mockarooValue.setFirstName(event.getFirst_name());
        mockarooValue.setLastName(event.getLast_name());
        mockarooValue.setEmail(event.getEmail());
        mockarooValue.setGender(event.getGender());
        mockarooValue.setIpAddress(event.getIp_address());

        mockarooKey.setId(event.getId());

        return new KeyValue<>(mockarooKey, mockarooValue);

    }
}
