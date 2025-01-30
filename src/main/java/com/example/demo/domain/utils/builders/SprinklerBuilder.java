package com.example.demo.domain.utils.builders;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.Sprinkler;
import com.example.demo.domain.models.SprinklerStatus;
import com.example.demo.domain.ports.Builder;
import com.example.demo.domain.ports.SprinklerBuilderPort;
import org.springframework.stereotype.Service;

@Service
public class SprinklerBuilder extends Builder<Sprinkler> implements SprinklerBuilderPort {

    public SprinklerBuilder() {
        instance = new Sprinkler();
    }

    @Override
    public SprinklerBuilder reset() {
        instance = new Sprinkler();
        return this;
    }

    @Override
    public SprinklerBuilder setCrop(Crop crop) {
        instance.setCrop(crop);
        return this;
    }

    @Override
    public SprinklerBuilder setStatus(SprinklerStatus status) {
        instance.setStatus(status);
        return this;
    }

    @Override
    public SprinklerBuilder setIrrigationType(IrrigationType irrigationType) {
        instance.setIrrigationType(irrigationType);
        return this;
    }

    @Override
    public SprinklerBuilder setFlowRate(float flowRate) {
        instance.setFlowRate(flowRate);
        return this;
    }

    @Override
    public Sprinkler build() {
        return instance;
    }
}
