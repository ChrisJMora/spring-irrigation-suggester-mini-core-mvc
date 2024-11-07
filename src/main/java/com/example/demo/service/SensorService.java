package com.example.demo.service;

import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SensorService {
    WrappedEntity<List<Sensor>> getAllSensors();
}
