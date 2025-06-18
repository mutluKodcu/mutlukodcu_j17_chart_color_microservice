package com.mutlukodcu.colorservice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutlukodcu.colorservice.domain.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ColorProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendColorCreatedEvent(Color color) {
        try {
            String event = objectMapper.writeValueAsString(color);
            kafkaTemplate.send("color-events", event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
