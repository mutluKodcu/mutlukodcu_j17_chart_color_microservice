package com.mutlukodcu.coloranalysisconsumer.consumer;

import com.mutlukodcu.shared.kafka.PhotoEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PhotoEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PhotoEventConsumer.class);
	
	@Autowired
    private ColorAnalysisService colorAnalysisService;

    @KafkaListener(topics = "photo-events", groupId = "color-analysis-group", containerFactory = "photoEventKafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, PhotoEvent> record) {
        PhotoEvent event = record.value();
        logger.info("🎯 Received PhotoEvent: photoId={}, filename={}, userId={}",
                event.getPhotoId(), event.getFilename(), event.getUserId());

        // Şimdilik sadece logla. Sonraki adımda işleme geçeceğiz.
    }
	
	@KafkaListener(topics = "photo-events", groupId = "color-analysis-group")
	public void listen(PhotoEvent photoEvent) {
		System.out.println("📥 PhotoEvent alındı: " + photoEvent.getFileName());
		colorAnalysisService.analyze(photoEvent);
	}
}
