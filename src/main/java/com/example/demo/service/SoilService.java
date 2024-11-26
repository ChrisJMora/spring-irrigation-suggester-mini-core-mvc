package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SoilService {
    /**
     * Get all soils from database, if the table is empty then throw an
     * exception.
     * @return List of the soils founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Soil> getAllSoils();
}
