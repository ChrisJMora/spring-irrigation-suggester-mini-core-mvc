package com.example.demo.dto;

import com.example.demo.model.agriculture.IrrigationType;
import com.example.demo.model.agriculture.SprinklerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SprinklerDTO {
    private IrrigationType irrigationType;
    private float caudal;
    private SprinklerStatus status;
}
