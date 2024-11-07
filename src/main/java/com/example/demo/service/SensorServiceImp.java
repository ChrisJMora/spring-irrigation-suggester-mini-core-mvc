package com.example.demo.service;

import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImp implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Override
    public WrappedEntity<List<Sensor>> getAllSensors() {
        return new WrappedEntity<>(sensorRepository.findAll());
    }
}
