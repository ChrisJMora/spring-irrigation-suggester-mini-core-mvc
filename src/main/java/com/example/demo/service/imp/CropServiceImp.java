package com.example.demo.service.imp;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.CropRepository;
import com.example.demo.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImp implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Override
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    @Override
    public WrappedEntity<Crop> saveCrop(Crop crop) {
        return new WrappedEntity<>(cropRepository.save(crop));
    }
}
