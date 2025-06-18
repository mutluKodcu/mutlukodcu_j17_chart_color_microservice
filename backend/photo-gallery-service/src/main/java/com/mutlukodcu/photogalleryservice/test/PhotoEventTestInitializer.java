package com.mutlukodcu.photogalleryservice.test;

import com.mutlukodcu.photogalleryservice.kafka.PhotoProducer;
import com.mutlukodcu.shared.kafka.PhotoEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PhotoEventTestInitializer implements CommandLineRunner {

    private final PhotoProducer photoProducer;

    public PhotoEventTestInitializer(PhotoProducer photoProducer) {
        this.photoProducer = photoProducer;
    }

    @Override
    public void run(String... args) {
        // Basit test mesajı gönder
        PhotoEvent event = new PhotoEvent();
        event.setPhotoId(UUID.randomUUID().toString());
        event.setUserId("test-user");
        event.setFileName("doga1.jpg");
        event.setImageUrl("/sample-images/doga1.jpg");

        System.out.println("📤 Test amaçlı PhotoEvent gönderiliyor...");
        photoProducer.sendPhotoEvent(event);
    }
}
