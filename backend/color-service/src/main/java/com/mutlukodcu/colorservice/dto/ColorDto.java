package com.mutlukodcu.colorservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorDto {
    private String name;
    private String hex;
    private String category;
}
