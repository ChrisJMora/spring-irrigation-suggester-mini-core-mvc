package com.example.demo.application.dto;

import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.SprinklerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SprinklerDTO {
    private IrrigationType irrigationType;
    private float caudal;
    private SprinklerStatus status;
}
