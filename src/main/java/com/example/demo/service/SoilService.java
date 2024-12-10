/**
 * Implementation service for managing soil data.
 * This service provides methods to perform operations related to soils in the database.
 */
package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SoilService {
    /**
     * Retrieves all soils from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of soils found in the database.
     * @throws EmptyTableException When the soils table has no records.
     */
    List<Soil> getAllSoils();

    /**
     * Retrieves a random soil from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A random soil from the database.
     * @throws EmptyTableException When the soils table has no records.
     */
    Soil getRandomSoil();
}
