package com.example.demo.service;

import com.example.demo.model.agriculture.Location;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface LocationService {
    WrappedEntity<List<Location>> getAllLocations();
}
