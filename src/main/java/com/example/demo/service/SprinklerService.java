package com.example.demo.service;

import com.example.demo.model.agriculture.Sprinkler;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SprinklerService {
    List<Sprinkler> getAllSprinklers();
}
