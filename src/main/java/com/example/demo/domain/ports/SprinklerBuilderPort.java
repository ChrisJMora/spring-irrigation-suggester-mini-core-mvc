package com.example.demo.domain.ports;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.SprinklerStatus;
import com.example.demo.domain.utils.builders.SprinklerBuilder;

public interface SprinklerBuilderPort {
    SprinklerBuilder setCrop(Crop crop);
    SprinklerBuilder setStatus(SprinklerStatus status);
    SprinklerBuilder setIrrigationType(IrrigationType irrigationType);
    SprinklerBuilder setFlowRate(float flowRate);
}
