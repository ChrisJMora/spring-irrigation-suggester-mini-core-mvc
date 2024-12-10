/**
 * Implementation service for managing locations.
 * This service provides methods to perform CRUD operations on locations in the database.
 */
package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Location;

import java.util.List;

public interface LocationService {
    /**
     * Retrieves all locations from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of locations found in the database.
     * @throws EmptyTableException When the locations table has no records.
     */
    List<Location> getAllLocations();

    /**
     * Retrieves a random location from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A random location from the database.
     * @throws EmptyTableException When the locations table has no records.
     */
    Location getRandomLocation();

    /**
     * Creates a random location with random latitude and longitude values.
     * The new location is then saved to the database.
     *
     * @return The newly created random location.
     * @throws SaveRecordFailException When the location could not be saved.
     */
    Location createRandomLocation();

    /**
     * Saves a location to the database.
     * If the location is not saved successfully, an exception is thrown.
     *
     * @param location The location to be saved.
     * @return The saved location.
     * @throws SaveRecordFailException When the location could not be saved.
     */
    Location saveLocation(Location location);
}
