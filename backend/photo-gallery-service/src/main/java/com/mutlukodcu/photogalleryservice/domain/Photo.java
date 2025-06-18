package com.mutlukodcu.photogalleryservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String category;

    private String filePath;

    private String userId; // JWT'den gelen kullanıcı ID
}
