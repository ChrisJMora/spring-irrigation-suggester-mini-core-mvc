package com.example.demo.service;

import com.example.demo.exception.UserNameOrEmailAreadyExistsException;
import com.example.demo.model.admin.UserEntity;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.CropRepository;
import com.example.demo.utils.AuthenticationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CropServiceImp implements CropService {

    @Autowired
    private CropRepository cropRepository;

    @Override
    public WrappedEntity<List<Crop>> getAllCrops() {
        return new WrappedEntity<>(cropRepository.findAll());
    }

    @Override
    public WrappedEntity<Crop> saveCrop(Crop crop) {
        return new WrappedEntity<>(cropRepository.save(crop));
    }
}
