package com.example.demo.domain.utils.converters;

import com.example.demo.application.ports.NormalDistributionService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NormalDistributionServiceImp implements NormalDistributionService {
    private final Random random = new Random();

    @Override
    public float generateNormal(float mean, float standardDeviation) {
        return mean + standardDeviation * (float)random.nextGaussian();
    }
}