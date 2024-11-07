package com.example.demo.service;

import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SoilService {
    WrappedEntity<List<Soil>> getAllSoils();
}
