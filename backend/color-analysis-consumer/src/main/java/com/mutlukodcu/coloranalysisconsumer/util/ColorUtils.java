package com.mutlukodcu.coloranalysisconsumer.util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;

public class ColorUtils {

    // Her bir pikselin rengini okuyup, en sık geçen ilk N tanesini döndürür
    public static List<Color> getDominantColors(BufferedImage image, int topN) {
        Map<Integer, Integer> colorCount = new HashMap<>();

        int width = image.getWidth();
        int height = image.getHeight();

        // Pikselleri gez
        for (int x = 0; x < width; x += 2) { // performans için her pikseli değil, her 2. pikseli alalım
            for (int y = 0; y < height; y += 2) {
                int rgb = image.getRGB(x, y);
                colorCount.put(rgb, colorCount.getOrDefault(rgb, 0) + 1);
            }
        }

        // En çok geçen renkleri bul
        return colorCount.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(topN)
                .map(entry -> new Color(entry.getKey()))
                .collect(Collectors.toList());
    }

    // Ortalama rengi hesapla
    public static Color getAverageColor(BufferedImage image) {
        long sumRed = 0, sumGreen = 0, sumBlue = 0;
        int count = 0;

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x += 2) {
            for (int y = 0; y < height; y += 2) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);
                sumRed += color.getRed();
                sumGreen += color.getGreen();
                sumBlue += color.getBlue();
                count++;
            }
        }

        if (count == 0) return new Color(0, 0, 0);

        return new Color(
                (int) (sumRed / count),
                (int) (sumGreen / count),
                (int) (sumBlue / count)
        );
    }

    public static String toHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
	  public static List<Color> extractDominantColors(String imagePath, int count) throws Exception {
        BufferedImage image = ImageIO.read(new File(imagePath));
        Map<Color, Integer> colorCountMap = new HashMap<>();

        for (int y = 0; y < image.getHeight(); y += 5) {
            for (int x = 0; x < image.getWidth(); x += 5) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);
                colorCountMap.merge(color, 1, Integer::sum);
            }
        }

        return colorCountMap.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())) // çoktan aza
                .limit(count)
                .map(Map.Entry::getKey)
                .toList();
    }
}
