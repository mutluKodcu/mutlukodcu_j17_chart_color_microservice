package com.mutlukodcu.shared.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorEvent {
    private String username;
    private String color;
    private String action; // CREATE, DELETE, UPDATE
}