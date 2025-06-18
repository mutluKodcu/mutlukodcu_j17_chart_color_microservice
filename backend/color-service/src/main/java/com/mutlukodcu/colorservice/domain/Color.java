package com.mutlukodcu.colorservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "colors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hex; // Örn: #FF5733

    private String category; // Örn: kırmızı, mavi, pastel vb.
}
