package com.example.demo.service;

import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.SensorRepository;
import com.example.demo.persistence.SoilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilServiceImp implements SoilService {

    @Autowired
    private SoilRepository soilRepository;

    @Override
    public WrappedEntity<List<Soil>> getAllSoils() {
        return new WrappedEntity<>(soilRepository.findAll());
    }
}
