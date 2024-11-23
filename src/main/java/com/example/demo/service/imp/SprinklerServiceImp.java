package com.example.demo.service.imp;

import com.example.demo.model.agriculture.Sprinkler;
import com.example.demo.persistence.SprinklerRepository;
import com.example.demo.service.SprinklerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprinklerServiceImp implements SprinklerService {

    @Autowired
    private SprinklerRepository sprinklerRepository;

    @Override
    public List<Sprinkler> getAllSprinklers() {
        return sprinklerRepository.findAll();
    }
}
