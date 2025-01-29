package com.example.demo.domain.utils.factories;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.Sprinkler;
import com.example.demo.domain.models.SprinklerStatus;
import com.example.demo.domain.utils.builders.SprinklerBuilder;

public class SprinklerFactory {

    private final SprinklerBuilder builder;

    public SprinklerFactory() {
        builder = new SprinklerBuilder();
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
