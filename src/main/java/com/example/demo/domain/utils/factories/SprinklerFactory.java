package com.example.demo.domain.utils.factories;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.Sprinkler;
import com.example.demo.domain.models.SprinklerStatus;
import com.example.demo.domain.ports.SprinklerBuilderPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprinklerFactory {

    private final SprinklerBuilderPort builder;

    @Autowired
    public SprinklerFactory(SprinklerBuilderPort builder) {
        this.builder = builder;
    }

    public Sprinkler create(Crop crop, IrrigationType irrigationType) {
        return builder
                .setCrop(crop)
                .setIrrigationType(irrigationType)
                .setFlowRate(irrigationType.getFlowRate())
                .setStatus(SprinklerStatus.OFF)
                .build();
    }
}
