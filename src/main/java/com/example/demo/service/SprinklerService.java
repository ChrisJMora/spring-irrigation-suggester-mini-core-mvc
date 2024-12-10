/**
 * Implementation service for managing sprinklers.
 * This service provides methods to perform operations related to sprinklers in the database.
 */
package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.IrrigationType;
import com.example.demo.model.agriculture.Sprinkler;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface SprinklerService {
    /**
     * Retrieves all sprinklers from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of sprinklers found in the database.
     * @throws EmptyTableException When the sprinklers table has no records.
     */
    List<Sprinkler> getAllSprinklers();

    /**
     * Creates a specified number of sprinklers associated with a given crop and irrigation type.
     *
     * @param crop The crop to associate with the new sprinklers.
     * @param quantity The number of sprinklers to create.
     * @param irrigationType The type of irrigation to be used by the sprinklers.
     * @return A list of the newly created sprinklers.
     */
    List<Sprinkler> createSprinklers(Crop crop, int quantity, IrrigationType irrigationType);

    /**
     * Saves a list of sprinklers in the database.
     * If any sprinkler in the list is not saved successfully, an exception is thrown.
     *
     * @param sprinklers The list of sprinklers to be saved.
     * @return A list of the saved sprinklers.
     * @throws SaveRecordFailException When any sprinkler could not be saved.
     */
    List<Sprinkler> saveSprinklers(List<Sprinkler> sprinklers);
}
