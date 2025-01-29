package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCropRequest {
    private CropDTO cropData;
    private int numberOfSensors;
    private int numberOfSprinklers;
    private String irrigationType;
}
