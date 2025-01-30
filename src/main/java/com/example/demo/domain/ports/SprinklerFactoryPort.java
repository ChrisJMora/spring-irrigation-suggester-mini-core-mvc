package com.example.demo.domain.ports;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.Sprinkler;

public interface SprinklerFactoryPort {
    Sprinkler create(Crop crop, IrrigationType irrigationType);
}
