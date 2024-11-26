package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CropDTO {
    private String name;
    private float waterRequired;
    private float rootHeight;
}
