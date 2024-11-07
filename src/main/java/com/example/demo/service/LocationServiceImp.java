package com.example.demo.service;

import com.example.demo.model.agriculture.Location;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public WrappedEntity<List<Location>> getAllLocations() {
        return new WrappedEntity<>(locationRepository.findAll());
    }
}
