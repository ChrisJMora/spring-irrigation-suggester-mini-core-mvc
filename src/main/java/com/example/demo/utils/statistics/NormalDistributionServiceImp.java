package com.example.demo.utils.statistics;

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