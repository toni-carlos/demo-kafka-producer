package com.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Event {
    private int anonymous_id;
    private String action;
    private String event_timestamp;
    private String event_datetime;
}
