package com.mutlukodcu.coloranalysisconsumer.producer;

import com.mutlukodcu.shared.kafka.AnalysisResultEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalysisResultProducer {

    private static final Logger logger = LoggerFactory.getLogger(AnalysisResultProducer.class);

    private final KafkaTemplate<String, AnalysisResultEvent> kafkaTemplate;

    private static final String TOPIC = "analysis-results";

    public void send(AnalysisResultEvent event) {
        kafkaTemplate.send(TOPIC, event.getPhotoId().toString(), event)
                .addCallback(
                        success -> logger.info("✅ AnalysisResultEvent sent: {}", event),
                        failure -> logger.error("❌ Failed to send AnalysisResultEvent", failure)
                );
    }
	private static final String TOPIC = "color-analysis-results";

    @Autowired
    private KafkaTemplate<String, AnalysisResultEvent> kafkaTemplate;

    public void send(AnalysisResultEvent result) {
        kafkaTemplate.send(TOPIC, result.getPhotoId(), result);
        System.out.println("📤 Renk analiz sonucu gönderildi: " + result);
    }
}
