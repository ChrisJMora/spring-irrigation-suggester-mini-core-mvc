package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Forecast;
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

    /**
     * Get all sprinklers from database, if the table is empty then throw an
     * exception.
     * @return List of the sprinklers founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Sprinkler> getAllSprinklers() {
        List<Sprinkler> sprinklers = sprinklerRepository.findAll();
        if (sprinklers.isEmpty()) throw new EmptyTableException(Sprinkler.class);
        return sprinklers;
    }
}
