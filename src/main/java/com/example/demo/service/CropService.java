package com.example.demo.service;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface CropService {
    List<Crop> getAllCrops();
    WrappedEntity<Crop> saveCrop(Crop crop);
}
