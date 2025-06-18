package com.mutlukodcu.coloranalysisconsumer.service;

import com.mutlukodcu.coloranalysisconsumer.util.ColorUtils;
import com.mutlukodcu.shared.kafka.AnalysisResultEvent;
import com.mutlukodcu.shared.kafka.PhotoEvent;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

@Service
public class ColorAnalysisService {
	@Autowired
	private AnalysisResultProducer analysisResultProducer;

    public AnalysisResultEvent analyzePhoto(PhotoEvent event) {
        try {
            // 1. Görseli dosyadan oku
            BufferedImage image = ImageIO.read(new File(event.getImagePath()));
            if (image == null) {
                throw new IOException("Image could not be read: " + event.getImagePath());
            }

            // 2. Ortalama renk
            Color averageColor = ColorUtils.getAverageColor(image);

            // 3. En baskın renkler (ilk 3)
            List<Color> dominantColors = ColorUtils.getDominantColors(image, 3);

            // 4. AnalysisResultEvent oluştur
            return AnalysisResultEvent.builder()
                    .photoId(event.getPhotoId())
                    .userId(event.getUserId())
                    .averageColor(ColorUtils.toHex(averageColor))
                    .dominantColors(dominantColors.stream()
                            .map(ColorUtils::toHex)
                            .toList())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("Image processing failed for: " + event.getImagePath(), e);
        }
    }
	public void analyze(PhotoEvent photoEvent) {
        System.out.println("🎨 Görsel analiz başlatılıyor: " + photoEvent.getImageUrl());

        try {
            String imagePath = "/app/sample-images/" + photoEvent.getFileName(); // Docker içi yol
            List<Color> dominantColors = ColorUtils.extractDominantColors(imagePath, 5); // ilk 5 rengi al

            System.out.println("🔍 Dominant renkler:");
            for (Color color : dominantColors) {
                String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
                System.out.println(" - RGB: " + color + " | HEX: " + hex);
            }
			// ... görselden dominant renkleri çıkar ...
			List<String> hexColors = dominantColors.stream()
					.map(c -> String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue()))
					.toList();

			AnalysisResultEvent resultEvent = new AnalysisResultEvent(
					photoEvent.getPhotoId(),
					hexColors
			);

			analysisResultProducer.send(resultEvent);

        } catch (Exception e) {
            System.err.println("❌ Renk analizi sırasında hata: " + e.getMessage());
        }
    }
}
