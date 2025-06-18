package com.mutlukodcu.coloranalysisconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ColorAnalysisConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ColorAnalysisConsumerApplication.class, args);
        System.out.println("🚀 Color Analysis Consumer başlatıldı.");
    }
}