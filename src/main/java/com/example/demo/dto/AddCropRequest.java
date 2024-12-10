package com.example.demo.dto;

import com.example.demo.model.agriculture.IrrigationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCropRequest {
    private CropDTO cropDTO;
    private int numberOfSensors;
    private int numberOfSprinklers;
    private IrrigationType irrigationType;
}
