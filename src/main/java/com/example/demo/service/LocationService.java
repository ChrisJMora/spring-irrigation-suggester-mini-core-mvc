package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Location;

import java.util.List;

public interface LocationService {
    /**
     * Get all locations from database, if the table is empty then throw an
     * exception.
     * @return List of the locations founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Location> getAllLocations();

    /**
     * Get a random location from the database, if the table is empty then
     * thrown an exception.
     * @return Random location from the database.
     * @exception EmptyTableException When the table have not records.
     */
    Location getRandomLocation();
}
