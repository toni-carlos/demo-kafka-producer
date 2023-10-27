package com.demo.transformer;

import com.demo.avro.UserEventsKey;
import com.demo.avro.UserEventsValue;
import com.demo.model.Event;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.KeyValue;
import org.springframework.stereotype.Component;

@Component
public class SchemaEnrich {



    public KeyValue<GenericRecord, GenericRecord> enrich(Event event) {

        var userEventsValue = new UserEventsValue();
        var userEventsKey = new UserEventsKey();

        userEventsValue.setAnonymousId(event.getAnonymous_id());
        userEventsValue.setAction(event.getAction());
        userEventsValue.setEventTimestamp(event.getEvent_timestamp());
        userEventsValue.setEventDatetime(event.getEvent_datetime());

        userEventsKey.setAnonymousId(event.getAnonymous_id());
        return new KeyValue<>(userEventsKey, userEventsValue);

    }
}
