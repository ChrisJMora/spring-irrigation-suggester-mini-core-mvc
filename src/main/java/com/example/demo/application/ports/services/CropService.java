/**
 * Implementation service for managing crops.
 * This service provides methods to perform CRUD operations on crops in the database.
 */
package com.example.demo.application.ports.services;

import com.example.demo.domain.exceptions.EmptyRecordException;
import com.example.demo.domain.exceptions.EmptyTableException;
import com.example.demo.domain.exceptions.SaveRecordFailException;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.Location;

import java.util.List;

public interface CropService {
    /**
     * Retrieves all crops from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of crops found in the database.
     * @throws EmptyTableException When the table has no records.
     */
    List<Crop> getAllCrops();

    /**
     * Retrieves a crop by its ID.
     *
     * @param id The ID of the crop to retrieve.
     * @return Crop The crop found.
     * @throws EmptyRecordException When no crop is found with the provided ID.
     */
    Crop getCropById(Long id);

    /**
     * Retrieves a crop by its location.
     *
     * @param location The location of the crop to retrieve.
     * @return Crop The crop found.
     * @throws EmptyRecordException When no crop is found at the provided location.
     */
    Crop getCropByLocation(Location location);

    /**
     * Creates or updates a crop in the database.
     * If the crop is not saved successfully, an exception is thrown.
     *
     * @param crop The crop to be saved.
     * @return Crop The saved crop.
     * @throws SaveRecordFailException When the record could not be saved.
     */
    public Crop saveCrop(Crop crop);
}
