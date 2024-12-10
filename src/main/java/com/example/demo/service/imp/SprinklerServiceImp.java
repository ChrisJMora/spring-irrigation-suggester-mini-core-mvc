/**
 * Implementation service for managing sprinklers.
 * This service provides methods to perform operations related to sprinklers in the database.
 */
package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.IrrigationType;
import com.example.demo.model.agriculture.Sprinkler;
import com.example.demo.model.agriculture.SprinklerStatus;
import com.example.demo.persistence.SprinklerRepository;
import com.example.demo.service.SprinklerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SprinklerServiceImp implements SprinklerService {

    @Autowired
    private SprinklerRepository sprinklerRepository;

    /**
     * Retrieves all sprinklers from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of sprinklers found in the database.
     * @throws EmptyTableException When the sprinklers table has no records.
     */
    @Override
    public List<Sprinkler> getAllSprinklers() {
        List<Sprinkler> sprinklers = sprinklerRepository.findAll();
        if (sprinklers.isEmpty()) throw new EmptyTableException(Sprinkler.class);
        return sprinklers;
    }

    /**
     * Creates a specified number of sprinklers associated with a given crop and irrigation type.
     *
     * @param crop The crop to associate with the new sprinklers.
     * @param quantity The number of sprinklers to create.
     * @param irrigationType The type of irrigation to be used by the sprinklers.
     * @return A list of the newly created sprinklers.
     */
    @Override
    public List<Sprinkler> createSprinklers(Crop crop, int quantity, IrrigationType irrigationType) {
        List<Sprinkler> sprinklers = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            Sprinkler sprinkler = new Sprinkler();
            sprinkler.setCrop(crop);
            sprinkler.setIrrigationType(irrigationType);
            sprinkler.setFlowRate(irrigationType.getFlowRate());
            sprinkler.setStatus(SprinklerStatus.OFF);
            sprinklers.add(sprinkler);
        }
        return saveSprinklers(sprinklers);
    }

    /**
     * Saves a list of sprinklers in the database.
     * If any sprinkler in the list is not saved successfully, an exception is thrown.
     *
     * @param sprinklers The list of sprinklers to be saved.
     * @return A list of the saved sprinklers.
     * @throws SaveRecordFailException When any sprinkler could not be saved.
     */
    @Override
    public List<Sprinkler> saveSprinklers(List<Sprinkler> sprinklers) {
        List<Sprinkler> savedSprinklers = sprinklerRepository.saveAll(sprinklers);
        for (Sprinkler sprinkler : savedSprinklers) {
            if (sprinkler.getId() == null) {
                throw new SaveRecordFailException(Sprinkler.class);
            }
        }
        return savedSprinklers;
    }
}