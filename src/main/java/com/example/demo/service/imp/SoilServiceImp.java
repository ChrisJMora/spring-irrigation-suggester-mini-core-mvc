package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.SoilRepository;
import com.example.demo.service.SoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoilServiceImp implements SoilService {

    @Autowired
    private SoilRepository soilRepository;

    /**
     * Get all soils from database, if the table is empty then throw an
     * exception.
     * @return List of the soils founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Soil> getAllSoils() {
        List<Soil> soils = soilRepository.findAll();
        if (soils.isEmpty()) throw new EmptyTableException(Soil.class);
        return soilRepository.findAll();
    }
}
