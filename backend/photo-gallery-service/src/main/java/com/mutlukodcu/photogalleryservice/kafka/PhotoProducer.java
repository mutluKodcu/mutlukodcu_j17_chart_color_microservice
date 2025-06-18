package com.mutlukodcu.photogalleryservice.kafka;

import com.mutlukodcu.shared.dto.PhotoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PhotoProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendPhotoEvent(PhotoDTO dto) {
        kafkaTemplate.send("photo-events", dto);
    }
}
