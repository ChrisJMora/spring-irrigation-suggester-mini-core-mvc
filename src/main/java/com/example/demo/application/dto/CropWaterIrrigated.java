package com.example.demo.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropWaterIrrigated {
    private CropDTO crop;
    private int totalWaterIrrigated;
}
